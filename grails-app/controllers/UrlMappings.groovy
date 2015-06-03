// Grails Security ~ MIT License (Open Source) ~ Copyright (c) 2015 by individual contributors

class UrlMappings {

   static mappings = {
      "/signup"  (controller: "security", action: "signup")
      "/signin"  (controller: "security", action: "signin")
      "/signout" (controller: "security", action: "signout")
      "/book"    (controller: "book")  //workaround to remove unsightly "index"
      "/$controller/$action?/$id?" {
         constraints {
            }
         }
      "/"   (view: "/index")
      "500" (view: "/error")
      "404" (view: "/notFound")
      }

}
