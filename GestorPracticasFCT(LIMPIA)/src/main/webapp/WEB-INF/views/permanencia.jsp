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
	<form:form modelAttribute="practicaSeleccionada">
		<form:hidden path="id" />
		<fieldset>
			<div>
				<div class="form-group">
					<form:label path="permanencia" for="permanencia"
						class="col-md-1 control-label">
									* <spring:message code="alumno.tabla.permanencia" />
					</form:label>
					<div class="col-md-3">
						<form:select path="permanencia">
							<form:option value="S">Si</form:option>
							<form:option value="N">No</form:option>
						</form:select>
					</div>
					<div class="col-md-3">
						<form:errors path="permanencia" cssClass="errores" />
					</div>
				</div>
				<div>
					<div class="col-md-2 col-md-offset-2 top-buffer">
						<button type="submit" class="btn btn-primary btn-block"
							title="<spring:message code="boton.guardar"/>"
							onclick="alert('Registrada permanencia')">
							<spring:message code="boton.guardar" />
						</button>
					</div>
				</div>
			</div>
		</fieldset>
	</form:form>
	<script type="text/javascript">
		function refreshAndClose() {
			window.opener.location.reload();
			this.close();
		}
	</script>
</body>
</html>