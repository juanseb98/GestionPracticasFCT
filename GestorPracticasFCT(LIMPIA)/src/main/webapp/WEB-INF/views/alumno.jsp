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
						<img
							src="http://www.iescristobaldemonroy.es/wordpress/wp-content/uploads/2017/10/titulo_sinlogo.jpg"
							alt="I.E.S. Cristóbal de Monroy">
					</h4>
				</div>


				<div class="columns large-12 medium-13 small-10">
					<nav id="top-navigation" class="right show-for-large-up">
						<div class="menu-menu-principal-container">
							<ul id="menu-menu-principal"
								class="maxflat-menu maxflat-top-menu">

								<li id="menu-item-582"
									class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
									href="<spring:message code="link.inicio"/> "> <i
										class="_mi _before dashicons dashicons-calendar-alt"
										aria-hidden="true"></i><span><spring:message
												code="menu.logOut" /></span></a></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>
		<div id="header">
			<h2>
				Bienvenido
				<c:out value="${alumnoForm.nombre}" />
			</h2>
		</div>
		<hr />
		<p>
			<spring:message code="relleno" />
		</p>


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