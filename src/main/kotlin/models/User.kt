package models

import java.util.Date
import java.util.Random

data class User(
    val id: Long,
    val name: String,
    val createdDate: Date? = Date(),
    val address: Address? = null
)

object UserFactory {
  fun generate(): User =  User(Random().nextInt(500).toLong(), "Teste-${Random().nextInt(500)}")

  fun generateList(): Array<User> = arrayOf(generate(), generate(), generate())
}