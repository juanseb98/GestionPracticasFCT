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
	<form:form modelAttribute="valoracionAlumno">
		<form:hidden path="id" />
		<fieldset>
			<div>
				<div class="form-group">
					<form:label path="comentario" for="comentario"
						class="col-md-1 control-label">
									* <spring:message code="valoracion.comentario" />
					</form:label>
					<div class="col-md-3">
						<form:input id="comentario" path="comentario" class="form-control" />
					</div>
					<div class="col-md-3">
						<form:errors path="comentario" cssClass="errores" />
					</div>
				</div>

				<div class="form-group">
					<form:label path="puntuacion" for="puntuacion"
						class="col-md-1 control-label">
									* <spring:message code="valoracion.puntuacion" />
					</form:label>
					<div class="col-md-3">
						<form:input type="number" id="puntuacion" path="puntuacion"
							class="form-control" min="1" max="10" />
					</div>
					<div class="col-md-3">
						<form:errors path="puntuacion" cssClass="errores" />
					</div>
				</div>
				<div>
					<div class="col-md-2 col-md-offset-2 top-buffer">
						<button type="submit" class="btn btn-primary btn-block"
							title="<spring:message code="boton.guardar"/>"
							onclick="alert('Actualizada la valoracion')">
							<spring:message code="boton.guardar" />
						</button>
					</div>
				</div>
			</div>
		</fieldset>
	</form:form>
</body>
</html>