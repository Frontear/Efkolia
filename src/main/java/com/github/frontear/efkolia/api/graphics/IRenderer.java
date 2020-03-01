package com.github.frontear.efkolia.api.graphics;

/**
 * A public interface which defines the contract of an OpenGL context. The creation and usage of a
 * valid context occurs here, as well as its destruction.
 */
public interface IRenderer {
    /**
     * Generates and pushes a valid OpenGL context.
     */
    void begin();

    /**
     * Pops the previously created OpenGL context, resetting the context to how it was before being
     * modified.
     */
    void end();

    /**
     * This should be referred to by any {@link IRenderable} objects that wish to draw within the
     * context.
     *
     * @return Whether a valid OpenGL context was created by this renderer, and has yet to be
     * closed.
     */
    boolean isActive();
}
