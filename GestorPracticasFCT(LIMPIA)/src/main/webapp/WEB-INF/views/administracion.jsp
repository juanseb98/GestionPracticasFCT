<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
							src="${pageContext.request.contextPath}/resources/gfx/web/bannerIesCristobalDeMonroy.jpg"
							alt="I.E.S. Cristóbal de Monroy">
					</h4>
				</div>


				<div class="columns large-12 medium-13 small-10">
					<nav id="top-navigation" class="right show-for-large-up">
					<div class="menu-menu-principal-container">
						<ul id="menu-menu-principal" class="maxflat-menu maxflat-top-menu">
							<li id="menu-item-582"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
								href="/GestorPracticasFCT/administracion/editarAlumnos"> <i
									class="_mi _before dashicons dashicons-calendar-alt"
									aria-hidden="true"></i><span><spring:message
											code="menu.editarAlumnos" /></span>
							</a></li>

							<li id="menu-item-582"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
								href="/GestorPracticasFCT/administracion/editarEmpresas"> <i
									class="_mi _before dashicons dashicons-calendar-alt"
									aria-hidden="true"></i><span><spring:message
											code="menu.empresaEdit" /></span></a></li>
							<li id="menu-item-582"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
								href="/GestorPracticasFCT/administracion/notificacion"
								target="popup"
								onClick="window.open(this.href, this.target, 'width=540,height=400'); return false;">
									<i class="_mi _before dashicons dashicons-calendar-alt"
									aria-hidden="true"></i><span><spring:message
											code="menu.notificaciones" /> <c:if
											test="${administradorForm.numeroNotificaciones > 0 }">
											<span class="red"><c:out
													value="${administradorForm.numeroNotificaciones }"></c:out></span>
										</c:if> </span>
							</a></li>

							<li id="menu-item-582"
								class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
								href="/GestorPracticasFCT/ "> <i
									class="_mi _before dashicons dashicons-calendar-alt"
									aria-hidden="true"></i><span><spring:message
											code="menu.logOut" /></span></a></li>


						</ul>
					</div>
					</nav>
				</div>
			</div>
		</div>
		<div>
			<div id="deslog"></div>
			<h1>
				<spring:message code="administracion.saludo" />
				<c:out value="${administradorForm.nombre }" />
			</h1>
			<p>
				<spring:message code="relleno" />
			</p>

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