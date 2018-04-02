package workshop.macdo

import org.scalatest.{FunSpec, Matchers}

class AppSpec extends FunSpec with Matchers {
  describe("App") {
    it("should work") {
      App.test() shouldBe true
    }
  }
}
