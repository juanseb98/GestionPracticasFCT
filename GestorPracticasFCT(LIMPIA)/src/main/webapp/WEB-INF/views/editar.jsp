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
							href="/GestorPracticasFCT/administracion/editarAlumnos"> <i
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
		<form:form modelAttribute="edicionAlumnoForm" id="alu">
			<div class="errores">
				<c:if test="${not empty errores}">
					<spring:message code="error.cumplimentadoIncorrecto" />
				</c:if>

			</div>
			<form:hidden path="contrasenia" id="contrasenia" />
			<div class="fondoFormulario">
				<div class="row">
					<div class="col-md-12">
						<fieldset class="fondoRecuadro">
							<div class="form-horizontal">
								<div class="form-group">
									<form:label path="dni" for="dni" class="col-md-1 control-label">
											*<spring:message code="administracion.alumno.dni" />
										<form:errors path="dni" cssClass="errores" />
										<c:if test="${not empty errorDni }">
											<div class="errores">
												<spring:message code="${errorDni }" />
											</div>
										</c:if>
									</form:label>
									<div class="col-md-3">
										<form:input id="dni" path="dni" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="nombre" for="nombre"
										class="col-md-1 control-label">
											*<spring:message code="administracion.alumno.nombre" />
										<form:errors path="nombre" cssClass="errores" />
										<c:if test="${not empty errorNombre }">
											<div class="errores">
												<spring:message code="${errorNombre }" />
											</div>
										</c:if>
									</form:label>
									<div class="col-md-3">
										<form:input id="nombre" path="nombre" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="email" for="email"
										class="col-md-1 control-label">
											*<spring:message code="administracion.alumno.email" />
										<form:errors path="email" cssClass="errores" />
										<c:if test="${not empty errorEmail }">
											<div class="errores">
												<spring:message code="${errorEmail }" />
											</div>
										</c:if>
									</form:label>
									<div class="col-md-3">
										<form:input id="email" path="email" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="telefono" for="telefono"
										class="col-md-1 control-label">
											*<spring:message code="administracion.alumno.telefono" />
										<form:errors path="telefono" cssClass="errores" />
										<c:if test="${not empty errorTelefono }">
											<div class="errores">
												<spring:message code="${errorTelefono }" />
											</div>
										</c:if>
									</form:label>
									<div class="col-md-3">
										<form:input id="telefono" path="telefono" class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="anioEstudio" for="anioEstudio"
										class="col-md-1 control-label">
											*<spring:message code="administracion.alumno.anio" />
										<form:errors path="anioEstudio" cssClass="errores" />
										<c:if test="${not empty errorAnioEstudio }">
											<div class="errores">
												<spring:message code="${errorAnioEstudio }" />
											</div>
										</c:if>
									</form:label>
									<div class="col-md-3">
										<form:input type="text" id="anioEstudio" path="anioEstudio"
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
	<script type="text/javascript">
		$(function() {
			$('#anioEstudio')
					.datepicker(
							{
								changeYear : true,
								showButtonPanel : true,
								dateFormat : 'yy',
								onClose : function(dateText, inst) {
									var year = $(
											"#ui-datepicker-div .ui-datepicker-year :selected")
											.val();
									$(this).datepicker('setDate',
											new Date(year, 1));
								}
							});
			$(".date-picker-year").focus(function() {
				$(".ui-datepicker-month").hide();
			});
		});
	</script>
</body>
</html>