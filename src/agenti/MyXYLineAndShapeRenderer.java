package agenti;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

public class MyXYLineAndShapeRenderer extends org.jfree.chart.renderer.xy.XYLineAndShapeRenderer{

	@Override
	protected void drawSecondaryPass(Graphics2D arg0, XYPlot arg1,
			XYDataset arg2, int arg3, int arg4, int arg5, ValueAxis arg6,
			Rectangle2D arg7, ValueAxis arg8, CrosshairState arg9,
			EntityCollection arg10) {
		// TODO Auto-generated method stub
		super.drawSecondaryPass(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8,
				arg9, arg10);
	}

}
