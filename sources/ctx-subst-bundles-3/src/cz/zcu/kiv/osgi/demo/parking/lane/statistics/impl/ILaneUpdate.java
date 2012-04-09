/**
 * This file is part of TODO 
 * http:// TODO
 * 
 * Copyright (c) 2008-2012 University of West Bohemia, Czech Republic.
 * 
 * See the NOTICE file distributed with this work for additional 
 * information regarding copyright ownership.  
 * 
 * The University of West Bohemia licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
MOVE RELEVANT TWO LINES TO THE START OF THE FILE COMMENT, 
DELETE THE REST OF THIS COMMENT

 * This file is part of OSGi Bundle Compatibility Checker (OBCC)
 * http://www.assembla.com/spaces/obcc/

 * This file is part of the Java Class Comparator project (JaCC)
 * http://www.assembla.com/spaces/jacc/
*/


package cz.zcu.kiv.osgi.demo.parking.lane.statistics.impl;

/**
 * Local interface for lane statistics updates.
 * 
 * @author Premek Brada (brada@kiv.zcu.cz)
 */
public interface ILaneUpdate
{
    public void vehiclesPassing(int cnt);
}
