/*
 * Copyright 2010, 2011 Institut Pasteur.
 * 
 * This file is part of ICY.
 * 
 * ICY is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ICY is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ICY. If not, see <http://www.gnu.org/licenses/>.
 */
package plugins.tutorial.vtk;

import icy.painter.Overlay;
import icy.painter.VtkPainter;
import vtk.vtkActor;
import vtk.vtkFollower;
import vtk.vtkPolyDataMapper;
import vtk.vtkProp;
import vtk.vtkVectorText;

/**
 * @author stephane
 */
public class VtkText3DPainter extends Overlay implements VtkPainter
{
    private vtkFollower textActor;

    public VtkText3DPainter()
    {
        super("VTK 3D text");

        init();
    }

    // init vtk objects
    private void init()
    {
        // Create the 3D text and the associated mapper and follower (a type of actor)
        // Position the text so it is displayed over the origin of the axes.
        final vtkVectorText atext = new vtkVectorText();
        atext.SetText("Origin");

        final vtkPolyDataMapper textMapper = new vtkPolyDataMapper();
        textMapper.SetInputConnection(atext.GetOutputPort());

        textActor = new vtkFollower();
        textActor.SetMapper(textMapper);
        textActor.SetScale(100, 100, 100);
        // textActor.AddPosition(0, -0.1, 0);
    }

    @Override
    public vtkProp[] getProps()
    {
        return new vtkActor[] {textActor};
    }
}
