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
		<form:form modelAttribute="alumnoForm" action="${formAction}"
			method="post">
			<form:hidden path="nombre" id="nombre" />
			<form:hidden path="dni" id="dni" />
			<form:hidden path="contrasenia" id="contrasenia" />
			<table>
				<tr>
					<td><form:label path="practicaSelected" for="practicaSelected"
							class="col-md-1 control-label">
							<spring:message code="alumno.practica.titulo" />
						</form:label></td>
					<td><select id="practicaSelected" name="practicaSelected"
						class="form-control">
							<c:forEach items="${alumnoForm.practicas}" var="practica">
								<option value="${practica.id}"
									${practica.id == alumnoForm.practicaSelected ? 'selected' : ''}>
									<c:out value="${practica.empresa.denominacion}" /></option>
							</c:forEach>
					</select></td>
					<td><button type="submit" class="btn btn-primary btn-block"
							title="<spring:message code="boton.filtrar"/>">
							<spring:message code="boton.filtrar" />
						</button></td>
				</tr>
			</table>
		</form:form>

		<c:if test="${not empty practicaSeleccionada}">
			<form:form>
				<table>
					<tr>
						<th><spring:message code="alumno.tabla.empresa" /></th>
						<th><spring:message code="alumno.tabla.horasDia" /></th>
						<th><spring:message code="alumno.tabla.TipoJornada" /></th>
						<th><spring:message code="alumno.tabla.permanencia" /></th>
						<th><spring:message code="alumno.tabla.TutorDocente" /></th>
						<th><spring:message code="alumno.tabla.TutorLaboral" /></th>
						<th><spring:message code="alumno.tabla.fecha" /></th>
						<th><spring:message code="alumno.tabla.valoracion" /></th>
					</tr>
					<tr>
						<td><c:out
								value="${practicaSeleccionada.empresa.denominacion }" /></td>
						<td><c:out value="${practicaSeleccionada.horasDia }" /></td>
						<td><c:out value="${practicaSeleccionada.tipoJornada }" /></td>
						<td>
							<table>
								<tr>
									<td><c:if
											test="${empty practicaSeleccionada.permanencia }">
											<spring:message code="nulo" />
										</c:if> <c:if test="${not empty practicaSeleccionada.permanencia }">
											<c:out value="${practicaSeleccionada.permanencia }" />
										</c:if></td>
									<td><a>aniadir</a></td>
								</tr>
							</table>
						</td>
						<td><c:out
								value="${practicaSeleccionada.tutorDocente.persona.nombre }" /></td>
						<td><c:out
								value="${practicaSeleccionada.tutorLaboral.persona.nombre }" /></td>
						<td><c:out value="${practicaSeleccionada.fecha }" /></td>
						<td><a href="/GestorPracticasFCT/alumno/valoracion"
							target="popup"
							onClick="window.open(this.href, this.target, 'width=500,height=400'); return false;"><spring:message
									code="alumno.tabla.valoracion" /></a>
					</tr>

				</table>
			</form:form>
		</c:if>

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