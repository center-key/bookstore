class UrlMappings {

   static mappings = {
      "/signin" (controller: "security", action: "signin")
      "/signup" (controller: "security", action: "signup")
      "/$controller/$action?/$id?" {
         constraints {
            }
         }
      "/"   (view: "/index")
      "500" (view: "/error")
      "404" (view: "/notFound")
      }

}
