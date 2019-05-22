package com.iescristobaldemonroy.gestorFct.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class UTF8ResourceBundleControl extends ResourceBundle.Control {

	@Override
	public ResourceBundle newBundle(final String baseName, final Locale locale, final String format,
			final ClassLoader loader, final boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {

		final String bundleName = this.toBundleName(baseName, locale);
		final String resourceName = this.toResourceName(bundleName, "properties");
		ResourceBundle bundle = null;
		InputStream stream = null;

		if (reload) {

			final URL url = loader.getResource(resourceName);

			if (url != null) {

				final URLConnection connection = url.openConnection();

				if (connection != null) {
					connection.setUseCaches(false);
					stream = connection.getInputStream();
				}
			}

		} else {
			stream = loader.getResourceAsStream(resourceName);
		}

		if (stream != null) {
			try {
				bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
			} finally {
				stream.close();
			}
		}

		return bundle;
	}

}
