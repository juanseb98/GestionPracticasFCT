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

	<form:form modelAttribute="editarEmpresaForm" id="reg">
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
								<form:label path="filtroCif" for="filtroCif"
									class="col-md-1 control-label">
									<spring:message code="administracion.empresa.cif" />
									<form:errors path="filtroCif" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="filtroCif" path="filtroCif"
										class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="filtroDenominacion" for="filtroDenominacion"
									class="col-md-1 control-label">
											*<spring:message code="administracion.empresa.denominacion" />
									<form:errors path="filtroDenominacion" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="filtroDenominacion" path="filtroDenominacion"
										class="form-control" />
								</div>
							</div>
						</div>

						<input type="submit"
							value="<spring:message code="boton.filtrar"/>" class="register" />
						<!--
						<input type="submit"
							value="<spring:message code="boton.limpiarFiltros"/>"
							class="register" onclick="limpiar();" />
						 TODO -->
					</fieldset>
					<br />
				</div>
			</div>
		</div>
	</form:form>

	<div>
		<table>
			<tr>
				<th><spring:message code="administracion.empresa.cif" /></th>
				<th><spring:message code="administracion.empresa.denominacion" /></th>
				<th><spring:message code="administracion.empresa.telefono" /></th>
				<th><spring:message code="administracion.empresa.centroTrabajo" /></th>
				<th><spring:message code="administracion.empresa.acciones" /></th>
			</tr>

			<c:forEach items="${listaEmpresas}" var="empresa">
				<c:if test="${not empty empresa }">
					<tr>
						<td><c:out value="${empresa.cif }" /></td>
						<td><c:out value="${empresa.denominacion }" /></td>
						<td><c:out value="${empresa.telefono }" /></td>
						<td><spring:url value="/administracion/editarCentroTrabajo"
								var="CentroURL">
								<spring:param name="id" value="${empresa.cif }" />
							</spring:url> <a class="enlaces" href="${CentroURL}"
							title="<spring:message code="administracion.empresa.centroTrabajo"/>">
								<img style="height: 16px; padding: 0px 0px 0px 8px;"
								alt="centroTrabajo"
								src="${pageContext.request.contextPath}/resources/gfx/web/oficina.png" />
								<spring:message code="administracion.empresa.centroTrabajo" />
						</a></td>
						<td><spring:url value="/administracion/editarEmpresas/editar"
								var="editURL">
								<spring:param name="id" value="${empresa.cif }" />
							</spring:url> <a class="enlaces" href="${editURL}"
							title="<spring:message code="boton.editar"/>"> <img
								style="height: 16px; padding: 0px 0px 0px 8px;" alt="editar"
								src="${pageContext.request.contextPath}/resources/gfx/web/edit.png" />editar
						</a></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
		<a class="button" href="editarEmpresas/editar">Crear nuevo Empresa</a>
	</div>
	<script>
		function limpiar() {
			$('#operacion').value = ('limpiarFiltros')
		}
	</script>
</body>
</html>