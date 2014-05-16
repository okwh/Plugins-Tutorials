package plugins.tutorial.training;

import icy.gui.dialog.MessageDialog;
import icy.image.IcyBufferedImage;
import icy.math.MathUtil;
import icy.plugin.abstract_.PluginActionable;
import icy.type.collection.array.Array1DUtil;

public class ModifyImagePlugin3 extends PluginActionable
{
    @Override
    public void run()
    {
        IcyBufferedImage image = getActiveImage();

        // check if an image is opened
        if (image == null)
        {
            MessageDialog.showDialog("This plugin needs an opened image.");
            return;
        }

        // get a direct reference on the XY planar image data array
        Object dataArray = image.getDataXY(0);

        // convert the data array in double type array for precise mathematical operations
        double[] doubleDataArray = Array1DUtil.arrayToDoubleArray(dataArray, image.isSignedDataType());

        // divide contents of the double data array by 2
        MathUtil.divide(doubleDataArray, 2d);

        // convert the double data array back to the original image data type
        Array1DUtil.doubleArrayToSafeArray(doubleDataArray, image.getDataXY(0), image.isSignedDataType());

        // notify the image that its data has been modified
        // (this actually trigger some internal recalculations and screen refresh)
        image.dataChanged();
    }
}
