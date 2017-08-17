package poc

import models.User
import models.UserFactory
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.CallLogging
import org.jetbrains.ktor.features.Compression
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.request.receive
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.delete
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.post
import org.jetbrains.ktor.routing.put
import org.jetbrains.ktor.routing.route

fun Application.main() {
  install(DefaultHeaders)
  install(Compression)
  install(CallLogging)
  install(GsonSupport) {
    setPrettyPrinting()
  }
  install(Routing) {

    route("api") {
      route("user") {
        get {
          call.respond(UserFactory.generateList())
        }
        get("{id}") {
          val id = call.parameters["id"] as? Long
          call.respond(UserFactory.generate())
        }
        post("/") {
          val user = call.receive<User>()
          call.respond(user.copy(id = 1))
        }
        put("{id}") {
          val id = call.parameters["id"] as? Long
          call.respond(Unit)
        }
        delete("{id}") {
          val id = call.parameters["id"] as? Long
          call.respond(HttpStatusCode.NoContent)
        }
      }
    }
  }
}