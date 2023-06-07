<%-- Grails Security Plugin ~ MIT License ~ Copyright (c) 2015 by individual contributors --%>
<g:form name="grails-security" action="${action}">

   <h1>${label}</h1>
   <p class=err-msg></p>
   <label>
      <span>Email:</span>
      <input type=email name=email placeholder="Your email address">
   </label>
   <label>
      <span>Password:</span>
      <input type=password name=password placeholder="Your password">
   </label>
   <button type=button>${label}</button>
   <nav>
      <g:if test="${action == 'authenticate'}">
         Don't have an account?&nbsp; <security:signUpLink />
      </g:if><g:else>
         Already have an account?&nbsp; <security:signInLink />
      </g:else>
   </nav>

   <style>
      #grails-security { padding-left: 20px; margin-bottom: 40px; }
      #grails-security .err-msg { height: 1.2em; color: firebrick; }
      #grails-security label { display: block; margin-bottom: 15px; }
      #grails-security label >span:first-child { display: inline-block; width: 120px; text-align: right; }
      #grails-security button { margin: 0px 0px 15px 124px; cursor: pointer; }
   </style>

   <script>
      const grailsSecurity = {
         component: globalThis.document.getElementById('grails-security'),
         action: function() {
            const elem = grailsSecurity.component;
            const params = {
               email:    elem.querySelector('[type=email]').value,
               password: elem.querySelector('[type=password]').value,
               };
            const handle = (json) => {
               if (json.auth)
                  window.location = json.redirect;
               else
                  elem.querySelector('.err-msg').textContent = json.message;
               };
            const url = elem.getAttribute('action');
            fetchJson.post(url, params, handle);
            },
         setup: function() {
            grailsSecurity.component.querySelector('button').click(grailsSecurity.action);
            grailsSecurity.component.querySelector('input[name=email]').focus();
            };
         };
      grailsSecurity.setup();
   </script>

</g:form>
