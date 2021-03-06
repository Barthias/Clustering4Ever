package clustering4ever.scala.clustering
/**
 * @author Beck Gaël
 */
import scala.collection.{mutable, GenSeq}
import scala.collection.parallel.mutable.ParArray

object JenksNaturalBreaks
{
  /**
   * Look at https://en.wikipedia.org/wiki/Jenks_natural_breaks_optimization for more details 
   * Return breaks position in the sorted GenSeq
   * @param sortedValues : a sorted GenSeq
   * @param desiredNumberCategories : number of breaks user desire
   * @return Indexes of breaks in sortedValues sequence
   *
   */
  def jenksBrks[T](sortedValues: GenSeq[T], desiredNumberCategories: Int)(implicit num: Numeric[T]) =
  {
    val nbCat = desiredNumberCategories - 1
    val nbValues = sortedValues.size
    var value = 0D
    var v = 0D
    var i3 = 0
    var i4 = 0

    val mat1 = Array.fill(nbValues)(Array.fill(nbCat)(1D))
    val mat2 = Array.fill(nbValues)(Array.fill(nbCat)(Double.MaxValue))
    
    (2 to nbValues).foreach{ l =>
      var s1 = 0D
      var s2 = 0D
      var w = 0D
      (1 to l).foreach{ m =>
        val i3 = l - m + 1
        value = num.toDouble(sortedValues(i3 - 1))
        s2 += value * value
        s1 += value
        w += 1
        v = s2 - (s1 * s1) / w
        i4 = i3 - 1
        if( i4 != 0 )
        {
          (2 to nbCat).foreach{ j =>
            if( mat2(l - 1)(j - 1) >= (v + mat2(i4 - 1)(j - 2)) )
            {
              mat1(l - 1)(j - 1) = i3
              mat2(l - 1)(j - 1) = v + mat2(i4 - 1)(j - 2)
            }
          }
        }
      }
     
      mat1(l - 1)(0) = 1
      mat2(l - 1)(0) = v
    }
          
    val kclass = (1 to nbCat).map(_.toDouble).toArray

    kclass(nbCat - 1) = nbValues

    def update(j: Int, k: Int, kclass: Array[Double]) =
    {
      val id = (mat1(k - 1)(j - 1)).toInt - 1
      kclass(j - 2) = id
      (id, kclass)      
    }

    @annotation.tailrec
    def go(n: Int, k: Int, kclass: Array[Double]): (Int, Array[Double]) =
    {
      val (kUpdt, kclassUpdt) = update(n, k, kclass)
      if( n > 2 ) go(n - 1, kUpdt, kclassUpdt)
      else (kUpdt, kclassUpdt)
    }

    val (_, kclass2) = go(nbCat, nbValues, kclass)

    val res = mutable.ArrayBuffer.empty[T]
    res += sortedValues.head

    (1 to nbCat).foreach( i => res += sortedValues(kclass2(i - 1).toInt - 1) )
    
    res.toVector
  }
}
