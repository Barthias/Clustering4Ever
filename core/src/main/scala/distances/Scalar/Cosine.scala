package clustering4ever.math.distances.scalar

import _root_.clustering4ever.math.distances.ContinuousDistances
import _root_.scala.math.{pow, sqrt}

/**
 * @author Beck Gaël
 **/
class Cosine extends ContinuousDistances
{

	private def norm(dot1: Vector[Double]) =
	{
		var preNorm = 0D
		for( i <- dot1.indices ) preNorm += pow(dot1(i), 2)
		sqrt(preNorm)
	}

	private def dotProd(dot1: Vector[Double], dot2: Vector[Double]) =
	{
		var dotProd = 0D
		for( i <- dot1.indices ) dotProd += dot1(i) * dot2(i)
		dotProd
	}

	/**
	  * The cosine distance implemented
	  * @return The cosine distance between dot1 and dot2
	  **/
	override def d(dot1: Vector[Double], dot2: Vector[Double]): Double =
	{
		val anorm = norm(dot1)
		val bnorm = norm(dot2)
		dotProd(dot1, dot2) / (anorm * bnorm)
	}
}





