<!doctype html>
<!-- Grails Security Plugin ~ MIT License ~ Copyright (c) 2015 by individual contributors -->
<html lang="en" class="no-js">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <title><g:layoutTitle default="Grails" /></title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <asset:stylesheet src="application.css" />
      <style>#user-account { float: right; margin: 4px 6px; }</style>
      <asset:javascript src="application.js" />
      <g:layoutHead />
   </head>
   <body>
      <div id="grailsLogo" role="banner">
         <a href="http://grails.org"><asset:image src="grails_logo.png" alt="Grails" /></a>
      </div>
      <div id=user-account>
         <security:authToggleLink />
      </div>
      <g:layoutBody />
      <div class="footer" role="contentinfo"></div>
      <div id="spinner" class="spinner" style="display:none;">
         <g:message code="spinner.alt" default="Loading&hellip;" />
      </div>
   </body>
</html>
