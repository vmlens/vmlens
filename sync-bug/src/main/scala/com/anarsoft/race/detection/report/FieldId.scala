package com.anarsoft.race.detection.report

class FieldId(val fieldId: Int) extends StaticMemoryAccessId {

  override def equals(other: Any): Boolean = other match {
    case that: FieldId =>
      that.canEqual(this) &&
        fieldId == that.fieldId
    case _ => false
  }

  private def canEqual(other: Any): Boolean = other.isInstanceOf[FieldId]

  override def hashCode(): Int = {
    val state = Seq(fieldId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
