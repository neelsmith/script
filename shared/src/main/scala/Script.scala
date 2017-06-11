package edu.holycross.shot.script


import edu.holycross.shot.cite._

import scala.scalajs.js
import js.annotation.JSExport



@JSExport  case class Script (urn: Cite2Urn,label: String,
  alphabetic: Set[Int],
  numeric: Set[Int],
  punctuation: Set[Int],
  editorial: Set[Int])  {


  /** Set of all valid code points in this script.
  */
  def valid: Set[Int] = {
    alphabetic ++ numeric ++ punctuation ++ editorial
  }

  /** True if codepoint is alphabetic.
  *
  * @param codepoint Codepoint to test.
  */
  def isAlphabetic(codepoint: Int)= {
    alphabetic contains codepoint
  }


  /** True if all codepoints in s are alphabetic.
  *
  * @param s String to test.
  */
  def isAlphabetic(s: String)= {
    val codepointSet = Script.codepointsForString(s).toSet
    codepointSet subsetOf alphabetic
  }


  /** True if codepoint is numeric.
  *
  * @param codepoint Codepoint to test.
  */
  def isNumeric(codepoint: Int)= {
    numeric contains codepoint
  }

  /** True if all codepoints in s are numeric.
  *
  * @param s String to test.
  */
  def isNumeric(s: String)= {
    val codepointSet = Script.codepointsForString(s).toSet
    codepointSet subsetOf numeric
  }

  /** True if codepoint is punctuation.
  *
  * @param codepoint Codepoint to test.
  */
  def isPunctuation(codepoint: Int)= {
    punctuation contains codepoint
  }

  /** True if all codepoints in s are punctuation.
  *
  * @param s String to test.
  */
  def isPunctuation(s: String)= {
    val codepointSet = Script.codepointsForString(s).toSet
    codepointSet subsetOf punctuation
  }

  /** True if codepoint is editorial.
  *
  * @param codepoint Codepoint to test.
  */
  def isEditorial(codepoint: Int)= {
    editorial contains codepoint
  }

  /** True if all codepoints in s are editorial.
  *
  * @param s String to test.
  */
  def isEditorial(s: String)= {
    val codepointSet = Script.codepointsForString(s).toSet
    codepointSet subsetOf editorial
  }



  /** True if all codepoints in s are editorial.
  *
  * @param codepoint Codepoint to test.
  */
  def isValid(codepoint: Int)= {
    valid contains codepoint
  }

  /** True if all codepoints in s are editorial.
  *
  * @param s String to test.
  */
  def isValid(s: String)= {
    val codepointSet = Script.codepointsForString(s).toSet
    codepointSet subsetOf valid
  }
}

object Script {


  /** Convert a string to a list of Unicode codepoints.
  *
  * @param s String to convert.
  * @param idx Index of codepoints to count from.
  * @param codepoints List of previously seen codepoints.
  */
  def codepointsForString (s: String, idx : Int = 0, codepoints: List[Int] = List[Int]()): List[Int] = {
  if (idx >= s.length) {
    codepoints.reverse
  } else {
    val cp = s.codePointAt(idx)
    codepointsForString(s, idx + java.lang.Character.charCount(cp), cp :: codepoints)
  }
}



  /** Create Script from lists of strings.
  */
  def apply(urn: Cite2Urn,
  label: String,
  alphabetic: List[String],
  numeric: List[String] = List[String](),
  punctuation: List[String] = List[String](),
  editorial: List[String] = List[String]()): Script = {

    val alpha = codepointsForString(alphabetic.mkString).toSet
    val num = codepointsForString(numeric.mkString).toSet
    val punct =codepointsForString(punctuation.mkString).toSet
    val editor =codepointsForString(editorial.mkString).toSet
    Script(urn,label,alpha,num,punct,editor)
  }
}
