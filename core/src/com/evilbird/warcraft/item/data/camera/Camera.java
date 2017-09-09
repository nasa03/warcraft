package com.evilbird.warcraft.item.data.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperty;
import com.evilbird.engine.item.ItemRoot;

import javax.inject.Inject;

import static com.badlogic.gdx.Gdx.graphics;

public class Camera extends Item
{
    private float originalZoom;
    private OrthographicCamera camera;

    @Inject
    public Camera()
    {
        camera = new OrthographicCamera(graphics.getWidth(), graphics.getHeight());
        camera.setToOrtho(false, 30, 20);
        camera.zoom = 1f;
        originalZoom = 1f;

        setId(new NamedIdentifier("Camera"));
        setType(new NamedIdentifier("Camera"));
        setPosition(camera.position.x, camera.position.y);
        setTouchable(Touchable.disabled);
        setVisible(false);
    }

    @Override
    public void setRoot(ItemRoot root)
    {
        root.getViewport().setCamera(camera);
    }

    public float getZoom()
    {
        return camera.zoom;
    }

    public float getOriginalZoom()
    {
        return originalZoom;
    }

    public void setZoom(float zoom)
    {
        camera.zoom = zoom;
    }

    public void setOriginalZoom(float originalZoom)
    {
        this.originalZoom = originalZoom;
    }

    @Override
    public Item hit(Vector2 position, boolean touchable)
    {
        return null;
    }

    @Override
    public void positionChanged()
    {
        camera.position.x = getX();
        camera.position.y = getY();
    }

    @Override
    public Object getProperty(ItemProperty property)
    {
        if (CameraProperties.Zoom.equals(property)){
            return getZoom();
        }
        else if (CameraProperties.OriginalZoom.equals(property)){
            return getOriginalZoom();
        }
        return super.getProperty(property);
    }

    @Override
    public void setProperty(ItemProperty property, Object value)
    {
        if (CameraProperties.Zoom.equals(property)){
            setZoom((Float) value);
        }
        else if (CameraProperties.OriginalZoom.equals(property)){
            setOriginalZoom((Float) value);
        }
        else{
            super.setProperty(property, value);
        }
    }
}