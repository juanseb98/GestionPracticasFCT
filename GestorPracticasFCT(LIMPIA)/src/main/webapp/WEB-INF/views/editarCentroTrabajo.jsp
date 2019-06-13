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
							href="/GestorPracticasFCT/administracion/editarEmpresas"> <i
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
	<div>
		<div>

			<h2 align="center">
				<spring:message code="administracion.empresa.centroTrabajo.titulo" />
				<c:out value="${empresa.denominacion }" />
			</h2>
		</div>
		<form:form modelAttribute="editarCentroTrabajoForm" id="reg">
			<form:hidden path="operacion" id="operacion" value="" />
			<form:hidden path="inicio" id="operacion" value="N" />
			<div class="fondoFormulario">
				<div class="row">
					<div class="col-md-12">
						<fieldset class="fondoRecuadro">
							<legend>
								<spring:message code="titulo.criteriosBusqueda" />
							</legend>
							<div class="form-horizontal">
								<div class="form-group">
									<form:label path="filtroCalle" for="filtroCalle"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.calle" />
										<form:errors path="filtroCalle" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="filtroCalle" path="filtroCalle"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="filtroCodigoPostal" for="filtroCodigoPostal"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.codigoPostal" />
										<form:errors path="filtroCodigoPostal" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="filtroCodigoPostal" path="filtroCodigoPostal"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="filtroNumero" for="filtroNumero"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.numero" />
										<form:errors path="filtroNumero" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="filtroNumero" path="filtroNumero"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="filtroLocalidad" for="filtroLocalidad"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.localidad" />
										<form:errors path="filtroLocalidad" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:select path="filtroLocalidad">
											<form:options items="${listaLocalidades }" />
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label path="filtroMunicipio" for="filtroMunicipio"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.municipio" />
										<form:errors path="filtroMunicipio" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:select path="filtroMunicipio">
											<form:options items="${listaMunicipios }" />
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label path="filtroPrincipal" for="filtroPrincipal"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.principal" />
										<form:errors path="filtroPrincipal" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:checkbox path="filtroPrincipal" />
									</div>
								</div>
							</div>
							<input type="submit"
								value="<spring:message code="boton.filtrar"/>" class="register" />

						</fieldset>
					</div>
				</div>
			</div>
		</form:form>
	</div>

	<div>
		<table id="data">
			<tr>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.descripcion" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.calle" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.numero" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.telefono" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.localidad" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.municipio" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.provincia" /></th>
				<th><spring:message
						code="administracion.empresa.centroTrabajo.principal" /></th>
				<th><spring:message code="administracion.acciones" /></th>
			</tr>

			<c:forEach items="${listaCentrosTrabajo }" var="centroTrabajo">
				<c:if test="${not empty centroTrabajo }">
					<tr>
						<td><c:out value="${centroTrabajo.descripcion }" /></td>
						<td><c:out value="${centroTrabajo.calle }" /></td>
						<td><c:out value="${centroTrabajo.numero }" /></td>
						<td><c:out value="${centroTrabajo.telefono }" /></td>
						<td><c:out value="${centroTrabajo.localidad }" /></td>
						<td><c:out value="${centroTrabajo.municipio }" /></td>
						<td><c:out value="${centroTrabajo.provincia }" /></td>
						<td><c:if test="${centroTrabajo.principal eq 'S' }">
								<input type="checkbox" readonly="readonly" checked
									onclick="javascript: return false;" />
							</c:if> <c:if test="${centroTrabajo.principal ne 'S' }">
								<input type="checkbox" readonly="readonly"
									onclick="javascript: return false;" />
							</c:if></td>
						<td><spring:url
								value="/administracion/editarCentroTrabajo/editarC"
								var="editURL">
								<spring:param name="id" value="${centroTrabajo.id}" />
							</spring:url> <a class="enlaces" href="${editURL}"
							title="<spring:message code="boton.editar"/>"> <img
								style="height: 16px; padding: 0px 0px 0px 8px;" alt="editar"
								src="${pageContext.request.contextPath}/resources/gfx/web/edit.png" />
								<spring:message code="administracion.editar" />
						</a> <spring:url value="/administracion/editarCentroTrabajo/eliminar"
								var="deleteURL">
								<spring:param name="id" value="${centroTrabajo.id}" />
							</spring:url> <a class="enlaces" href="${deleteURL}"
							title="<spring:message code="administracion.borrar"/>"> <img
								style="height: 16px; padding: 0px 0px 0px 8px;" alt="editar"
								src="${pageContext.request.contextPath}/resources/gfx/web/delete.png" />
								<spring:message code="administracion.borrar" />
						</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<a class="register button" href="editarCentroTrabajo/editarC"><spring:message
				code="administracion.empresa.centroTrabajo.boton.crearCentroTrabajo" /></a>
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