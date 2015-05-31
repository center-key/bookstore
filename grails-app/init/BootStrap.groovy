class BootStrap {

   def securityService

   def init = { servletContext ->
      securityService.bootstrap()
      }

   def destroy = {
      }

}
