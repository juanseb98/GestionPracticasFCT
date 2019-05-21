<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="newInteresado" id="reg">
		<div class="fondoFormulario">
			<div class="row">
				<div class="col-md-12">
					<fieldset class="fondoRecuadro">
						<div class="form-horizontal">
							<div class="form-group">
								<form:label path="nombre" for="nombre"
									class="col-md-1 control-label">
											*<spring:message code="interesado.nombre" />
									<form:errors path="nombre" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="nombre" path="nombre" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="telefono" for="telefono"
									class="col-md-1 control-label">
											*<spring:message code="interesado.telefono" />
									<form:errors path="telefono" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="telefono" path="telefono" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="email" for="email"
									class="col-md-1 control-label">
											*<spring:message code="interesado.correo" />
									<form:errors path="email" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="email" path="email" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<form:label path="empresa" for="empresa"
									class="col-md-1 control-label">
											*<spring:message code="interesado.empresa" />
									<form:errors path="empresa" cssClass="errores" />
								</form:label>
								<div class="col-md-3">
									<form:input id="empresa" path="empresa" class="form-control" />
								</div>
							</div>

						</div>

						<input type="submit" value="<spring:message code="boton.enviar"/>"
							class="register" />
					</fieldset>
					<br />
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>