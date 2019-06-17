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
							href="/GestorPracticasFCT/administracion/editarAlumno"> <i
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
		<form:form modelAttribute="practicaForm" id="practica">

			<div class="form-group">
				<form:label path="dniAlumno" for="dniAlumno"
					class="col-md-1 control-label">
					<spring:message code="administracion.alumno.dni" />
					<form:errors path="dniAlumno" cssClass="errores" />
				</form:label>
				<div class="col-md-3">
					<form:input id="dniAlumno" path="dniAlumno" class="form-control"
						disabled="true" />
				</div>
			</div>

			<div class="form-group">
				<form:label path="cifEmpresa" for="cifEmpresa"
					class="col-md-1 control-label">
					<spring:message code="administracion.empresa.cif" />
					<form:errors path="cifEmpresa" cssClass="errores" />
				</form:label>
				<div class="col-md-3">
					<form:select path="cifEmpresa" id="cifEmpresa">
						<c:forEach items="${listaEmpresas}" var="empresa">
							<form:option value="${empresa.cif }">
								<c:out value="${empresa.denominacion }" />
							</form:option>
						</c:forEach>
					</form:select>
				</div>
			</div>

			<div class="form-group" id="combo2">
				<form:select path="dniTutorLaboral" name="dniTutorLaboral"
					id="dniTutorLaboral" disabled="disabled">
				</form:select>
			</div>

			<div class="form-group">
				<form:label path="tareas" for="tareas"
					class="col-md-1 control-label">
					<spring:message code="administracion.practica.tareas" />
					<form:errors path="tareas" cssClass="errores" />
				</form:label>
				<div class="col-md-3">
					<form:input id="tareas" path="tareas" class="form-control"
						disabled="true" />
				</div>
			</div>

			<div class="form-group">
				<form:label path="tipoJornada" for="tipoJornada"
					class="col-md-1 control-label">
					<spring:message code="administracion.practica.tipoJornada" />
					<form:errors path="tipoJornada" cssClass="errores" />
				</form:label>
				<div class="col-md-3">
					<form:select path="tipoJornada">
						<form:option value="partida">
							partida
						</form:option>
						<form:option value="completa">
							completa
						</form:option>
					</form:select>
				</div>
			</div>

		</form:form>
	</div>
	<script type="text/javascript" language="javascript">
	function cargarCiudad(obj) {
		$.ajax({
			url : "cargarCiudad",
			method : "POST",
			data : {
				filtroPais : $(obj).val()
			},
			dataType : "json",
			success : function(response) {
				$("#selectFiltroCiudad option:not(:disabled)").remove();
				var select = document.getElementById('selectFiltroCiudad');
				$.each(response, function(index, option) {
					console.log("option: " + option);
					select.options[select.options.length] = new Option(option,
							option);
				});

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("Error");
			}
		});
	};

	$(document)
	.ready(function() {

var contexPath = "<%=request.getContextPath()%>
		";

							$('#cifEmpresa')
									.change(
											function(e) {

												if (jQuery(this).val() != "-1") {
													$('#dniTutorLaboral')
															.find('option')
															.remove()
															.end()
															.append(
																	'<option value="-1">--Select state--</option>');

													e.preventDefault();
													var val = $(this).val();
													jQuery("#dniTutorLaboral")
															.removeAttr(
																	"disabled");

													alert(val);
													//$('#othstate').val('').hide();
													$
															.ajax({
																type : "POST",
																url : contexPath
																		+ '/loadTutorLaboral.htm',
																dataType : 'json',
																data : {
																	cifEmpresa : val
																},
																success : function(
																		data) {
																	showUsers(data.listaTutoresLaborales);
																	// $('#states').html( data.lstStates );
																},
																error : function(
																		e) {
																	alert('Error: '
																			+ e);
																}
															});
												} else {
													$("#dniTutorLaboral").attr(
															"disabled",
															"disabled");
													$('#dniTutorLaboral')
															.find('option')
															.remove()
															.end()
															.append(
																	'<option value="-1">--Select state--</option>');
												}
											});

							function showUsers(data) {

								for (var i = 0, len = data.length; i < len; ++i) {
									var tutorLaboral = data[i];
									$('#dniTutorLaboral').append(
											"<option value=\"" +tutorLaboral.dni + "\">"
													+ tutorLaboral.dni
													+ "</option>");
								}
							}

						});
	</script>
</body>
</html>