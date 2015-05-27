/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista.interfaz;

import java.util.HashMap;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.UsuarioDTO;

/**
 * @author Fernando Enrique Avendaño Hernández.
 * @version 1 18 Mayo 2015.
 */
public interface InterfaceGrupo {

    InterfaceVista getPadre();

    public void mostrarAlumnos(List<UsuarioDTO> listaAlumnos);

    public void mostrarMaestros(HashMap<CursoDTO, UsuarioDTO> maestros);
}
