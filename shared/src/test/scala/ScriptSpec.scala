
package edu.holycross.shot.script

import edu.holycross.shot.cite._

import org.scalatest.FlatSpec

class ScriptSpec extends FlatSpec {


  val alpha = List("abc")
  val num = List("123")
  val punct = List(".,?")
  val ed = List("[]")
  val urn = Cite2Urn("urn:cite2:script:demo:test1")
  val label = "Toy values to test Script object"

  "The Script object" should "instantiate a Script from string values" in {
    val script = Script(urn,label,alpha,num,punct,ed)
    script match {
      case s: Script => assert(true)
      case _ => fail("should have created a Script")
    }
  }

  "A Script" should "identify all alphabetic codepoints" in {
    val script = Script(urn,label,alpha,num,punct,ed)
    val expected  = Set(97, 98, 99)
    assert(script.alphabetic == expected)
  }

  it should "identify all numeric codepoints" in {
    val script = Script(urn,label,alpha,num,punct,ed)
    val expected  = Set(49, 50, 51)
    assert(script.numeric == expected)
 }


  it should "identify all punctuation codepoints" in {
    val script = Script(urn,label,alpha,num,punct,ed)
    val expected = Set(46, 44, 63)
    assert(script.punctuation == expected)
  }

  it should "identify all editorial codepoints" in {
   val script = Script(urn,label,alpha,num,punct,ed)
   val expected  = Set(91, 93)
   assert(script.editorial == expected)
  }

}
