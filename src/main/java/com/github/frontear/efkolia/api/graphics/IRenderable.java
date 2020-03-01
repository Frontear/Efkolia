package com.github.frontear.efkolia.api.graphics;

/**
 * An object that represents the drawing of a graphic through OpenGL. It should only be used in the
 * situation that it possess a valid context, which is created via {@link IRenderer}. The object
 * should ideally be immutable, and not be modified in terms of dimensions
 */
public interface IRenderable {
    /**
     * Renders a graphic through OpenGL. This must handle the situation where its parent {@link
     * IRenderer} is not active, and not attempt to draw if such a case arises.
     */
    void render();
}
