--       _________ __                 __                               
--      /   _____//  |_____________ _/  |______     ____  __ __  ______
--      \_____  \\   __\_  __ \__  \\   __\__  \   / ___\|  |  \/  ___/
--      /        \|  |  |  | \// __ \|  |  / __ \_/ /_/  >  |  /\___ \ 
--     /_______  /|__|  |__|  (____  /__| (____  /\___  /|____//____  >
--             \/                  \/          \//_____/            \/ 
--  ______________________                           ______________________
--                        T H E   W A R   B E G I N S
--         Stratagus - A free fantasy real time strategy game engine
--
--      campaign2.ccl - Define the orc campaign 2.
--
--      (c) Copyright 2002-2006 by Lutz Sammer and Jimmy Salmon
--
--      This program is free software; you can redistribute it and/or modify
--      it under the terms of the GNU General Public License as published by
--      the Free Software Foundation; either version 2 of the License, or
--      (at your option) any later version.
--  
--      This program is distributed in the hope that it will be useful,
--      but WITHOUT ANY WARRANTY; without even the implied warranty of
--      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
--      GNU General Public License for more details.
--  
--      You should have received a copy of the GNU General Public License
--      along with this program; if not, write to the Free Software
--      Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
--
--      $Id: campaign2.lua 1385 2006-10-15 18:30:49Z jsalmon3 $

--=============================================================================
--	Define the campaign

campaign_steps = {
  CreatePictureStep("../campaigns/orc-exp/interface/Act_I_-_Draenor,_the_Red_World.png", "Act I", "Draenor, the Red World"),
  CreateMapStep("campaigns/orc-exp/levelx01o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx02o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx03o.smp"),

  CreatePictureStep("../campaigns/orc-exp/interface/Act_II_-_The_Burning_of_Azeroth.png", "Act II", "The Burning of Azeroth"),
  CreateMapStep("campaigns/orc-exp/levelx04o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx05o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx06o.smp"),

  CreatePictureStep("../campaigns/orc-exp/interface/Act_III_-_The_Great_Sea.png", "Act III", "The Great Sea"),
  CreateMapStep("campaigns/orc-exp/levelx07o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx08o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx09o.smp"),

  CreatePictureStep("../campaigns/orc-exp/interface/Act_IV_-_Prelude_to_New_Worlds.png", "Act IV", "Prelude to New Worlds"),
  CreateMapStep("campaigns/orc-exp/levelx10o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx11o.smp"),
  CreateMapStep("campaigns/orc-exp/levelx12o.smp")
}
