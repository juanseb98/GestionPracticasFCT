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
		<form:form modelAttribute="edicionEmpresaForm" id="alu">
			<div class="errores">
				<c:if test="${not empty errores}">
					<spring:message code="error.cumplimentadoIncorrecto" />
				</c:if>

			</div>
			<div class="fondoFormulario">
				<div class="row">
					<div class="col-md-12">
						<fieldset class="fondoRecuadro">
							<div class="form-horizontal">
								<div class="form-group">
									<form:label path="cif" for="cif" class="col-md-1 control-label">
											*<spring:message code="administracion.empresa.cif" />
										<form:errors path="cif" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<c:if test="${empty edicionEmpresaForm.cif }">
											<form:input id="cif" path="cif" class="form-control" />
										</c:if>
										<c:if test="${not empty edicionEmpresaForm.cif }">
											<form:input id="cif" path="cif" class="form-control"
												disabled="true" />
										</c:if>

									</div>
								</div>
								<div class="form-group">
									<form:label path="denominacion" for="denominacion"
										class="col-md-1 control-label">
											*<spring:message code="administracion.empresa.denominacion" />
										<form:errors path="denominacion" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="denominacion" path="denominacion"
											class="form-control" />
									</div>
								</div>
								<div class="form-group">
									<form:label path="telefono" for="telefono"
										class="col-md-1 control-label">
											*<spring:message code="administracion.empresa.telefono" />
										<form:errors path="telefono" cssClass="errores" />
									</form:label>
									<div class="col-md-3">
										<form:input id="telefono" path="telefono" class="form-control" />
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