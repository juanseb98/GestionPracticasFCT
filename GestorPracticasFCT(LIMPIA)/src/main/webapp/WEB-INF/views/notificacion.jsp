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
	<c:if test="${not empty listaNotificaciones}">
		<form:form modelAttribute="administradorForm">
			<form:hidden path="dni" />
			<form:hidden path="nombre" />
			<form:hidden path="numeroNotificaciones" />
			<table>
				<tr>
					<th><spring:message code="administracion.tabla.tipo" /></th>
					<th><spring:message code="administracion.tabla.mensaje" /></th>
					<th><spring:message code="administracion.tabla.referencia" /></th>
					<th><spring:message code="administracion.tabla.marcarLeido" /></th>
				</tr>

				<c:forEach items="${listaNotificaciones}" var="notificacion">
					<tr>
						<td><c:out value="${notificacion.tipo }" /></td>
						<td><c:out value="${notificacion.mensaje }" /></td>
						<c:if test="${not empty notificacion.interesado}">
							<td><c:out value="${notificacion.interesado.nombre }" />
						</c:if>
						<c:if test="${not empty notificacion.valoracion}">
							<td><c:out value="${notificacion.valoracion.id }" />
						</c:if>
						<jsp:useBean id="notificacion"
							class="com.iescristobaldemonroy.gestorFct.entity.Notificacion" />
						<td><a
							href="/GestorPracticasFCT/administracion/leido?id=${notificacion.id }"><spring:message
									code="administracion.tabla.marcarLeido" /></a>
					</tr>
				</c:forEach>

			</table>
			<div class="col-md-2 col-md-offset-2 top-buffer">
				<button type="submit" formmethod="post"
					class="btn btn-primary btn-block"
					title="<spring:message code="boton.salir"/>"
					onclick="refreshAndClose() ">
					<spring:message code="boton.salir" />
				</button>
			</div>
		</form:form>

	</c:if>
	<c:if test="${empty listaNotificaciones}">
		<form:form modelAttribute="administradorForm">
			<form:hidden path="dni" />
			<form:hidden path="nombre" />
			<form:hidden path="numeroNotificaciones" />
			<h1>
				<spring:message code="notificacion.sinNotificacion" />
			</h1>
			<div class="col-md-2 col-md-offset-2 top-buffer">
				<button type="submit" formmethod="post"
					class="btn btn-primary btn-block"
					title="<spring:message code="boton.salir"/>"
					onclick="refreshAndClose();">
					<spring:message code="boton.salir" />
				</button>
			</div>
		</form:form>
	</c:if>
</body>
</html>
<script type="text/javascript">
	function refreshAndClose() {
		window.opener.location.reload();
		this.close();
	}
</script>