package com.evilbird.warcraft.action;

import com.evilbird.engine.action.ActionIdentifier;

public enum ActionCategory implements ActionIdentifier
{
    Select,
    Move,

    Pan,
    Zoom,
    Drag,

    Gather,
    Repair,

    Build,
    BuildSite,
    Train,
    Upgrade,

    Attack,
    Stop,
    Cancel,

    Unknown
}