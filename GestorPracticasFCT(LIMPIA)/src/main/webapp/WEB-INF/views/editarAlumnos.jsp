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
	<form:form modelAttribute="editarAlumnoForm" id="reg">
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
							class="register" onclick="" />
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
				<tr>
					<td><c:out value="${alumno.persona.dni }" /></td>
					<td><c:out value="${alumno.persona.nombre }" /></td>
					<td><c:out value="${alumno.telefono }" /></td>
					<td><spring:message code="nulo" /></td>
					<td><a>editar</a> <a>crear Practica</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>