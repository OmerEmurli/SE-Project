package de.htwg.se.battleship2.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FieldSpec extends AnyWordSpec {

  "A Battleship field" when {
    "initialized Empty" should {
      val field1 = new Field(1, 1, Filled.Empty)
      val field2 = new Field(3, 2, Filled.Empty)
      val field3 = new Field(3, 3, Filled.Empty)

      "have a bar as String of form '+---+---+" in {
        field2.bar() should be("+---+---+\n")
      }

      "have a scalable bar" in {
        field1.bar(1, 1) should be("+-+\n")
        field2.bar(2, 1) should be("+--+\n")
        field3.bar(2, 2) should be("+--+--+\n")
      }
       "have cells as String of form '| - | - | - | 1'" in {
         field2.cells(0, 1) should be("|-|-|-| 1\n")
        
      }

      "have scalable cells" in {
        field2.cells(0, 3) should be("| - | - | - | 1\n")
        field2.cells(1, 2) should be("|-|-|-| 2\n")
        
      }
       

      "have a mesh with specific dimensions" in {
      
        val expectedMesh =
        "  A   B   C\n" +
        "+---+---+---+\n" +
        "| - | - | - | 1\n" +
        "+---+---+---+\n" +
        "| - | - | - | 2\n" +
        "+---+---+---+\n" 
        

        field2.mesh(3) should be(expectedMesh)
    }


               
   }
 }
}