<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title><spring:message code="tituloPestana" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/screen.css"/>" />
</head>

<body>
	<div id="container">

		<div id="top-bar" class="top-bar home-border">
			<!-- Banner	 -->
			<div class="row">
				<div class="columns large-4 medium-3  small-6">
					<h4 class="smartlib-logo-header">
						<a href="<spring:message code="link.inicio"/> "
							title="I.E.S. Cristóbal de Monroy" rel="home"
							class="smartlib-site-logo image-logo"> <img
							src="http://www.iescristobaldemonroy.es/wordpress/wp-content/uploads/2017/10/titulo_sinlogo.jpg"
							alt="I.E.S. Cristóbal de Monroy">
						</a>
					</h4>
				</div>


				<div class="columns large-12 medium-13 small-10">
					<nav id="top-navigation" class="right show-for-large-up">
						<div class="menu-menu-principal-container">
							<ul id="menu-menu-principal"
								class="maxflat-menu maxflat-top-menu">
								<li id="menu-item-35"
									class="menu-item menu-item-type-custom menu-item-object-custom menu-item-home menu-item-35"><a
									href="<spring:message code="link.inicio"/> "><i
										class="_mi _before dashicons dashicons-store"
										aria-hidden="true"></i><span><spring:message
												code="menu.inicio" /></span></a></li>
								<li id="menu-item-582"
									class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
									href="<spring:message code="link.LogIn"/> "> <i
										class="_mi _before dashicons dashicons-calendar-alt"
										aria-hidden="true"></i><span><spring:message
												code="menu.login" /></span></a></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>

		<!-- Contenido de pagina -->
		<div id="content">
			<h1>
				<spring:message code="inicio.titulo" />
			</h1>
			<hr />
			<div>
				<p>
					<spring:message code="relleno" />
				</p>
			</div>

			<div class="galeria">
				<img
					src="http://blog.tradesmen.ie/wp-content/uploads/2012/07/programmers360.jpg"
					width="260" height="300" alt="Img1" /> <img
					src="http://www.responsabilidadsocial.net/wp-content/uploads/2015/05/images_articulos_2015_rse_buenas_condiciones_laborales.jpg"
					width="260" height="300" alt="Img2" /> <img
					src="https://practicas.um.es/practicas/img_2017/02.empresas.jpg"
					width="260" height="300" alt="Img3" /> <img
					src="http://noticias.universia.net.mx/net/images/practicas-empleo/l/la/la-/la-importancia-de-la-practica-profesional.jpg"
					width="260" height="300" alt="Img4" />
			</div>
			<hr />
			<h2>
				<spring:message code="inicio.interesado.titulo" />
			</h2>

			<p>
				<spring:message code="inicio.interesado.mensaje" />
			</p>

			<form:form modelAttribute="newInteresado" id="reg">
				<div class="fondoFormulario">
					<div class="row">
						<div class="col-md-12">
							<fieldset class="fondoRecuadro">
								<div class="form-horizontal">
									<div class="form-group">
										<form:label path="nombre" for="nombre"
											class="col-md-1 control-label">
											*<spring:message code="interesado.nombre" />
											<form:errors path="nombre" cssClass="errores" />
										</form:label>
										<div class="col-md-3">
											<form:input id="nombre" path="nombre" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:label path="telefono" for="telefono"
											class="col-md-1 control-label">
											*<spring:message code="interesado.telefono" />
											<form:errors path="telefono" cssClass="errores" />
										</form:label>
										<div class="col-md-3">
											<form:input id="telefono" path="telefono"
												class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:label path="email" for="email"
											class="col-md-1 control-label">
											*<spring:message code="interesado.correo" />
											<form:errors path="email" cssClass="errores" />
										</form:label>
										<div class="col-md-3">
											<form:input id="email" path="email" class="form-control" />
										</div>
									</div>
									<div class="form-group">
										<form:label path="empresa" for="empresa"
											class="col-md-1 control-label">
											*<spring:message code="interesado.empresa" />
											<form:errors path="empresa" cssClass="errores" />
										</form:label>
										<div class="col-md-3">
											<form:input id="empresa" path="empresa" class="form-control" />
										</div>
									</div>

								</div>

								<input type="submit"
									value="<spring:message code="boton.enviar"/>" class="register" />
							</fieldset>
							<br />
						</div>
					</div>
				</div>
			</form:form>
		</div>
		<!-- Pie de pagina -->
		<div id="main-footer">
			<p>
				<spring:message code="piePagina" />
				<br />
			</p>
		</div>
	</div>
</body>
</html>
