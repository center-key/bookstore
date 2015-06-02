<%-- Grails Security ~ MIT License (Open Source) ~ Copyright (c) 2015 by individual contributors  --%>

<html>
<head>
<meta name="layout" content="main">
<title>Sign In</title>
</head>
<body>

<g:render template="userForm" model="[label: 'Sign in', action: 'authenticate']" />

</body>
</html>
