package clustering4ever.math.distances.scalar

import clustering4ever.math.distances.ContinuousDistances
import _root_.scala.math.{pow, sqrt}

/**
 * @author Beck Gaël
 **/
class Cosine extends ContinuousDistances
{

	private def norm(dot1: Seq[Double]) =
	{
		sqrt(( for( i <- 0 until dot1.size ) yield( pow(dot1(i), 2) ) ).reduce(_ + _))
	}

	private def dotProd(dot1: Seq[Double], dot2: Seq[Double]) =
	{
		var dotProd = 0D
		for( i <- dot1.indices ) dotProd += dot1(i) * dot2(i)
		dotProd
	}

	/**
	  * The famous Minkowski distance implemented
	  * @return The Minkowski distance between dot1 and dot2
	  **/
	override def distance(dot1: Seq[Double], dot2: Seq[Double]): Double =
	{
		val anorm = norm(dot1)
		val bnorm = norm(dot2)
		dotProd(dot1, dot2) / (anorm * bnorm)
	}
}





