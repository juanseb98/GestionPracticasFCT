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
					<ul id="menu-menu-principal" class="maxflat-menu maxflat-top-menu">
						<li id="menu-item-582"
							class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
							href="/GestorPracticasFCT/administracion"> <i
								class="_mi _before dashicons dashicons-calendar-alt"
								aria-hidden="true"></i><span><spring:message
										code="menu.volver" /></span>
						</a></li>
					</ul>
				</div>
				</nav>
			</div>
		</div>
	</div>

	<form:form modelAttribute="editarAlumnoForm" id="reg">
		<form:hidden path="operacion" id="operacion" val="" />
		<div class="fondoFormulario">
			<div class="row">
				<div class="col-md-12">
					<fieldset class="fondoRecuadro">
						<legend>
							<spring:message code="titulo.criteriosBusqueda" />
						</legend>
						<div class="form-horizontal">
							<div class="form-group">
								<form:label path="filtroDni" for="filtroDni"
									class="col-md-1 control-label">
									<spring:message code="administracion.alumno.dni" />
									<form:errors path="filtroDni" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="filtroDni" path="filtroDni"
										class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="filtroNombre" for="filtroNombre"
									class="col-md-1 control-label">
											*<spring:message code="administracion.alumno.nombre" />
									<form:errors path="filtroNombre" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="filtroNombre" path="filtroNombre"
										class="form-control" />
								</div>
							</div>
						</div>

						<input type="submit"
							value="<spring:message code="boton.filtrar"/>" class="register" />
						<input type="submit"
							value="<spring:message code="boton.limpiarFiltros"/>"
							class="register"
							onclick="$('#operacion').value = ('limpiarFiltros');" />
						<!-- TODO -->
					</fieldset>
					<br />
				</div>
			</div>
		</div>
	</form:form>

	<div>
		<table>
			<tr>
				<th><spring:message code="administracion.alumno.dni" /></th>
				<th><spring:message code="administracion.alumno.nombre" /></th>
				<th><spring:message code="administracion.alumno.telefono" /></th>
				<th><spring:message code="administracion.alumno.email" /></th>
				<th><spring:message code="administracion.alumno.acciones" /></th>
			</tr>

			<c:forEach items="${listaAlumnos}" var="alumno">
				<c:if test="${not empty alumno }">
					<tr>
						<td><c:out value="${alumno.persona.dni }" /></td>
						<td><c:out value="${alumno.persona.nombre }" /></td>
						<td><c:out value="${alumno.telefono }" /></td>
						<td><a href="mailto:<c:out value="${alumno.email }"/>"><c:out
									value="${alumno.email }" /></a></td>
						<td><spring:url value="/administracion/editarAlumnos/editar"
								var="editURL">
								<spring:param name="id" value="${alumno.dni}" />
							</spring:url> <a class="enlaces" href="${editURL}"
							title="<spring:message code="boton.editar"/>"> <img
								style="height: 16px; padding: 0px 0px 0px 8px;" alt="editar"
								src="${pageContext.request.contextPath}/resources/gfx/web/edit.png" />editar
						</a> <a><img style="height: 16px; padding: 0px 0px 0px 8px;"
								alt="practica"
								src="${pageContext.request.contextPath}/resources/gfx/web/contract.png" />crear
								Practica</a> <spring:url
								value="/administracion/editarAlumnos/restaurar" var="restart" />
							<a class="enlaces" href="${restart}"><img
								style="height: 16px; padding: 0px 0px 0px 8px;" alt="practica"
								src="${pageContext.request.contextPath}/resources/gfx/web/access.png" />Restaurar
								Contraseña</a></td>
					</tr>
				</c:if>
			</c:forEach>

		</table>
	</div>
</body>
</html>