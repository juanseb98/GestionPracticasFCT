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

<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
</head>
<body>
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
									<spring:message code="administracion.alumno.nombre" />
									<form:errors path="filtroNombre" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="filtroNombre" path="filtroNombre"
										class="form-control" />
								</div>
							</div>

							<div class="form-group">
								<form:label path="filtroAnio" for="filtroAnio"
									class="col-md-1 control-label">
									<spring:message code="administracion.alumno.anio" />
									<form:errors path="filtroNombre" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:select path="filtroAnio">
										<form:options items="${listaAnios }" />
									</form:select>
								</div>
							</div>
						</div>

						<input type="submit"
							value="<spring:message code="boton.filtrar"/>" class="register" />
						<!-- TODO
						<input type="submit"
							value="<spring:message code="boton.limpiarFiltros"/>"
							class="register" onclick="limpiar();" />
						 -->
					</fieldset>
					<br />
				</div>
			</div>
		</div>
	</form:form>

	<div>
		<table class="display" id=data style="width: 100%;">
			<tr>
				<th><spring:message code="administracion.alumno.dni" /></th>
				<th><spring:message code="administracion.alumno.nombre" /></th>
				<th><spring:message code="administracion.alumno.telefono" /></th>
				<th><spring:message code="administracion.alumno.email" /></th>
				<th><spring:message code="administracion.alumno.anio" /></th>
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
						<td><c:out value="${alumno.anioEstudio }"></c:out>
						<td><spring:url value="/administracion/editarAlumnos/editar"
								var="editURL">
								<spring:param name="id" value="${alumno.dni}" />
							</spring:url> <a class="enlaces" href="${editURL}"
							title="<spring:message code="boton.editar"/>"> <img
								style="height: 16px; padding: 0px 0px 0px 8px;" alt="editar"
								src="${pageContext.request.contextPath}/resources/gfx/web/edit.png" />
								<spring:message code="administracion.editar" />
						</a> </td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<a class="button" href="editarAlumnos/editar"><spring:message
				code="administracion.alumno.boton.crearAlumno" /></a>
	</div>
	<script>
		$(document)
				.ready(
						function() {
							$('#data').after(
									'<div id="nav" class="alignright"></div>');
							var rowsShown = 10;
							var rowsTotal = $('#data tbody tr').length;
							var numPages = rowsTotal / rowsShown;
							for (i = 0; i < numPages; i++) {
								var pageNum = i + 1;
								$('#nav').append(
										'<a class="solid" href="#" rel="'+i+'">'
												+ pageNum + '</a> ');
							}
							$('#data tbody tr').hide();
							$('#data tbody tr').slice(0, rowsShown).show();
							$('#nav a:first').addClass('active');
							$('#nav a').bind(
									'click',
									function() {

										$('#nav a').removeClass('active');
										$(this).addClass('active');
										var currPage = $(this).attr('rel');
										var startItem = currPage * rowsShown;
										var endItem = startItem + rowsShown;
										$('#data tbody tr').css('opacity',
												'0.0').hide().slice(startItem,
												endItem).css('display',
												'table-row').animate({
											opacity : 1
										}, 300);
									});
						});
		function limpiar() {
			$('#operacion').value = ('limpiarFiltros')
		}
	</script>
</body>
</html>
