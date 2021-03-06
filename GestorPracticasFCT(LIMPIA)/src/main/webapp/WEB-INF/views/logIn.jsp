<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<title><spring:message code="tituloPestana" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/screen.css"/>" />
</head>

<body>
	<div id="container">
		<div id="top-bar" class="top-bar home-border">
			<!-- Banner	 -->
			<div class="row">
				<div class="columns large-4 medium-3  small-6">
					<h4 class="smartlib-logo-header">
						<a href="<spring:message code="link.inicio"/> "
							title="I.E.S. Cristóbal de Monroy" rel="home"
							class="smartlib-site-logo image-logo"> <img
							src="${pageContext.request.contextPath}/resources/gfx/web/bannerIesCristobalDeMonroy.jpg"
							alt="I.E.S. Cristóbal de Monroy">
						</a>
					</h4>
				</div>


				<div class="columns large-12 medium-13 small-10">
					<nav id="top-navigation" class="right show-for-large-up">
						<div class="menu-menu-principal-container">
							<ul id="menu-menu-principal"
								class="maxflat-menu maxflat-top-menu">
								<li id="menu-item-35"
									class="menu-item menu-item-type-custom menu-item-object-custom menu-item-home menu-item-35"><a
									href="<spring:message code="link.inicio"/> "><i
										class="_mi _before dashicons dashicons-store"
										aria-hidden="true"></i><span><spring:message
												code="menu.inicio" /></span></a></li>
								<li id="menu-item-582"
									class="menu-item menu-item-type-post_type menu-item-object-page menu-item-582"><a
									href="<spring:message code="link.LogIn"/> "> <i
										class="_mi _before dashicons dashicons-calendar-alt"
										aria-hidden="true"></i><span><spring:message
												code="menu.login" /></span></a></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>

		<div id="Contenedor">
			<c:if test="${not empty error}">
				<span class="errores"><c:out value="${error}" /></span>
			</c:if>
			<div class="Icon">
				<!--Icono de usuario-->
				<span class="glyphicon glyphicon-user"><img
					src="http://iescristobaldemonroy.es/joomlaantiguo/images/stories/monroy.gif"
					alt="I.E.S. Cristóbal de Monroy"></span>
			</div>
			<div class="ContentForm">

				<form:form modelAttribute="newMember" id="reg">
					<div class="input-group input-group-lg">

						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-envelope"></i></span>
						<form:label path="dni" for="dni">
							*<spring:message code="logIn.dni" />
							<div class="popup" onclick="myFunction()">
								(?) <span class="popuptext" id="myPopup">dni</span>
							</div>
							<form:errors path="dni" cssClass="errores" />
						</form:label>
						<br>
						<form:input path="dni" class="form-control" />
					</div>
					<br>
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-lock"></i></span>
						<form:label path="password" for="password">
							*<spring:message code="logIn.password" />
							<form:errors path="password" cssClass="errores" />
						</form:label>
						<br>
						<form:input type="password" class="form-control" id="password"
							placeholder="password" path="password" />

					</div>
					<input type="submit"
						value="<spring:message code="boton.conectar"/> " class="register" />
					<br>
				</form:form>
			</div>
		</div>
	</div>


	<!-- Pie de pagina -->
	<div id="main-footer">
		<p>
			<spring:message code="piePagina" />
			<br />
		</p>
	</div>
</body>
</html>

<script type="text/javascript">
	// When the user clicks on div, open the popup
	function myFunction() {
		var popup = document.getElementById("myPopup");
		popup.classList.toggle("show");
	}
</script>
