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
							href="/GestorPracticasFCT/administracion/editarCentroTrabajo?id=${cif }">
								<i class="_mi _before dashicons dashicons-calendar-alt"
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
		<form:form modelAttribute="edicionCentroTrabajoForm" id="alu">
			<div class="errores">
				<c:if test="${not empty errores}">
					<spring:message code="error.cumplimentadoIncorrecto" />
				</c:if>
			</div>
			<div class="fondoFormulario">
				<div class="row">
					<div class="col-md-12">
						<div class="errores">
							<c:if test="${not empty error }">
								<spring:message code="error.noControladoDatos" />
							</c:if>
						</div>
						<fieldset class="fondoRecuadro">
							<div class="form-horizontal">
								<fieldset class="fondoRecuadro">
									<legend>Descripcion de la sede</legend>
									<div class="form-group">
										<form:label path="descripcion" for="descripcion"
											class="col-md-1 control-label">
											<div class="errores">*</div>
											<spring:message
												code="administracion.empresa.centroTrabajo.descripcion" />
											<form:errors path="descripcion" cssClass="errores" />
										</form:label>
										<div class="col-md-3">
											<form:input id="descripcion" path="descripcion"
												class="form-control" />
										</div>
									</div>
								</fieldset>
								<div class="form-group">
									<form:label path="calle" for="calle"
										class="col-md-1 control-label">
										<div class="errores">*</div>
										<spring:message
											code="administracion.empresa.centroTrabajo.calle" />
										<form:errors path="calle" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="calle" path="calle" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="codigoPostal" for="codigoPostal"
										class="col-md-1 control-label">
										<div class="errores">*</div>
										<spring:message
											code="administracion.empresa.centroTrabajo.codigoPostal" />
										<form:errors path="codigoPostal" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="codigoPostal" path="codigoPostal"
											class="form-control" />
									</div>
								</div>

								<div class="form-group">
									<form:label path="email" for="email"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.email" />
										<form:errors path="email" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="email" path="email" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="escalera" for="escalera"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.escalera" />
										<form:errors path="escalera" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="escalera" path="escalera"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="letra" for="letra"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.letra" />
										<form:errors path="letra" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="letra" path="letra"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="localidad" for="localidad"
										class="col-md-1 control-label">
										<div class="errores">*</div>
										<spring:message
											code="administracion.empresa.centroTrabajo.localidad" />
										<form:errors path="localidad" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="localidad" path="localidad"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="municipio" for="municipio"
										class="col-md-1 control-label">
										<div class="errores">*</div>
										<spring:message
											code="administracion.empresa.centroTrabajo.municipio" />
										<form:errors path="municipio" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="municipio" path="municipio"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="numero" for="numero"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.numero" />
										<form:errors path="numero" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="numero" path="numero"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="piso" for="piso"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.piso" />
										<form:errors path="piso" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="piso" path="piso"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">

									<spring:message
										code="administracion.empresa.centroTrabajo.principal" />
									<div class="col-md-3">
										<form:select path="principal">
											<form:option value="S">Si</form:option>
											<form:option value="N">No</form:option>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<form:label path="provincia" for="provincia"
										class="col-md-1 control-label">
										<div class="errores">*</div>
										<spring:message
											code="administracion.empresa.centroTrabajo.provincia" />
										<form:errors path="provincia" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="provincia" path="provincia"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="telefono" for="telefono"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.telefono" />
										<form:errors path="telefono" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="telefono" path="telefono"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="tipoVia" for="tipoVia"
										class="col-md-1 control-label">
										<spring:message
											code="administracion.empresa.centroTrabajo.tipoVia" />
										<form:errors path="tipoVia" cssClass="tipoVia" />
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="tipoVia" path="tipoVia"
											class="form-control" />
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
</body>
</html>