package com.github.mliarakos

import com.typesafe.config.ConfigFactory
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ShoconFallbackSpec extends AnyFlatSpec with Matchers {

  it should "load from multiple chained fallback configs" in {
    val a = ConfigFactory.parseString(
      """
        |lib {
        |  message = a
        |  state = true
        |  value = 10
        |}
        |""".stripMargin
    )
    val b = ConfigFactory.parseString(
      """
        |lib {
        |  message = b
        |  state = false
        |}
        |""".stripMargin
    )
    val c = ConfigFactory.parseString(
      """
        |lib {
        |  message = c
        |}
        |""".stripMargin
    )

    val cba = c.withFallback(b).withFallback(a)
    cba.getString("lib.message") shouldBe "c"
    cba.getBoolean("lib.state") shouldBe false
    cba.getInt("lib.value") shouldBe 10
  }

  it should "load from multiple tiered fallback configs" in {
    val a = ConfigFactory.parseString(
      """
        |lib {
        |  message = a
        |  state = true
        |  value = 10
        |}
        |""".stripMargin
    )
    val b = ConfigFactory.parseString(
      """
        |lib {
        |  message = b
        |  state = false
        |}
        |""".stripMargin
    )
    val c = ConfigFactory.parseString(
      """
        |lib {
        |  message = c
        |}
        |""".stripMargin
    )

    val ba = b.withFallback(a)
    ba.getString("lib.message") shouldBe "b"
    ba.getBoolean("lib.state") shouldBe false
    ba.getInt("lib.value") shouldBe 10

    val cba = c.withFallback(ba)
    cba.getString("lib.message") shouldBe "c"
    cba.getBoolean("lib.state") shouldBe false
    cba.getInt("lib.value") shouldBe 10
  }

}
