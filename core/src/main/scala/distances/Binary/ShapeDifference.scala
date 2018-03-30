package clustering4ever.math.distances.binary

import clustering4ever.math.distances.{BinaryDistance, BinaryDistanceUtil}
import _root_.scala.math.pow

/**
 * @author Beck Gaël
 **/
class ShapeDifference extends BinaryDistance
{
	override def distance(vector1: Seq[Int], vector2: Seq[Int]): Double =
	{
		val (a, b, c, d) = BinaryDistanceUtil.contingencyTable(vector1, vector2)
		((a + b + c + d) * (b + c) - pow(b - c, 2)) / pow(a + b + c + d,2)
	}	
}