# Implementation Details

This document provides detailed technical information about the N-Body simulation implementation, including class design, physics calculations, and algorithm specifics.

## Architecture Overview

The simulation consists of two main classes that work together to create the gravitational simulation:

- **`CelestialBody.java`**: Models individual celestial objects with their physical properties and behaviors
- **`NBody.java`**: Manages the simulation loop, file I/O, and graphics rendering

## CelestialBody Class Implementation

### Instance Variables

The `CelestialBody` class contains six private instance variables:

- `double myXPos` - Current x-coordinate position
- `double myYPos` - Current y-coordinate position  
- `double myXVel` - Current x-velocity component
- `double myYVel` - Current y-velocity component
- `double myMass` - Mass of the celestial body
- `String myFileName` - Image file name for visualization

### Constructor

The constructor takes six parameters and initializes all instance variables:

```java
public CelestialBody(double xP, double yP, double xV, double yV, double m, String f)
```

### Getter Methods

Six getter methods provide access to private instance variables:
- `getX()`, `getY()` - Position accessors
- `getXVel()`, `getYVel()` - Velocity accessors  
- `getMass()` - Mass accessor
- `getName()` - Filename accessor

### Physics Calculation Methods

#### Distance Calculation (`calcDistance`)

Calculates Euclidean distance between two celestial bodies using the standard distance formula:

```java
public double calcDistance(CelestialBody b)
```

Implementation uses:
```
r² = dx² + dy²
r = √(dx² + dy²)
```

Where:
- `dx = b.myXPos - this.myXPos`
- `dy = b.myYPos - this.myYPos`

#### Force Calculation (`calcForceExertedBy`)

Computes gravitational force between two bodies using Newton's law of universal gravitation:

```java
public double calcForceExertedBy(CelestialBody b)
```

Implementation:
```
F = G * m₁ * m₂ / r²
```

Where:
- `G = 6.67e-11` (gravitational constant)
- `m₁ = this.myMass`
- `m₂ = b.myMass`  
- `r = calcDistance(b)`

#### Force Component Calculations

**X-Component Force (`calcForceExertedByX`)**:
```java
public double calcForceExertedByX(CelestialBody b)
```

**Y-Component Force (`calcForceExertedByY`)**:
```java
public double calcForceExertedByY(CelestialBody b)
```

These decompose the total gravitational force into directional components:

```
Fₓ = F * (dx / r)
Fᵧ = F * (dy / r)
```

#### Net Force Calculations

**Net X-Force (`calcNetForceExertedByX`)**:
**Net Y-Force (`calcNetForceExertedByY`)**:

These methods sum forces from all other bodies in the system:

```java
public double calcNetForceExertedByX(CelestialBody[] bodies)
public double calcNetForceExertedByY(CelestialBody[] bodies)
```

Implementation includes a crucial check to prevent self-interaction:
```java
if (!this.equals(bodies[i])) {
    // Calculate and sum force
}
```

#### Position Update (`update`)

Updates position and velocity using Euler integration:

```java
public void update(double deltaT, double xforce, double yforce)
```

Four-step process:
1. Calculate acceleration: `ax = xforce / myMass`, `ay = yforce / myMass`
2. Calculate new velocity: `nvx = myXVel + deltaT * ax`, `nvy = myYVel + deltaT * ay`
3. Calculate new position: `nx = myXPos + deltaT * nvx`, `ny = myYPos + deltaT * nvy`
4. Update instance variables: Assign new values to `myXPos`, `myYPos`, `myXVel`, `myYVel`

**Important**: All new values must be calculated before updating any instance variables to avoid order dependency issues.

## NBody Class Implementation

### Static Methods

The `NBody` class contains only static methods:

#### File Reading Methods

**`readRadius(String filename)`**:
- Opens and reads the universe radius from a data file
- Returns the second value in the file (first is number of bodies)
- Uses `Scanner` for file parsing

**`readBodies(String filename)`**:
- Reads celestial body data and creates `CelestialBody[]` array
- Parses each line: x, y, xvel, yvel, mass, filename
- Returns array of initialized `CelestialBody` objects

#### Main Simulation Loop

**`main(String[] args)`**:

The simulation follows this structure:

1. **Initialization**:
   - Parse command line arguments for time parameters and filename
   - Read universe radius and celestial bodies from file
   - Set up graphics canvas with proper scaling

2. **Simulation Loop**:
   ```java
   for (double t = 0.0; t < totalTime; t += dt) {
       // Calculate forces for all bodies
       // Update positions and velocities  
       // Render current frame
   }
   ```

3. **Force Calculation Phase**:
   - Create `xForces[]` and `yForces[]` arrays
   - For each body, calculate net forces from all other bodies
   - Store results in force arrays

4. **Update Phase**:
   - For each body, call `update()` with time step and forces
   - Bodies update their positions and velocities

5. **Rendering Phase**:
   - Clear canvas and redraw background
   - Draw each body at its current position
   - Display the frame

## Physics Implementation Details

### Numerical Integration

The simulation uses **Euler's method** for numerical integration:

- **Advantages**: Simple implementation, computationally efficient
- **Limitations**: First-order accuracy, potential instability with large time steps
- **Time Step Sensitivity**: Smaller `dt` provides better accuracy but requires more computation

### Gravitational Constant

Uses `G = 6.67e-11` in SI units (N⋅m²/kg²), consistent with real physics.

### Coordinate System

- **Origin**: Center of the universe (graphics canvas center)
- **X-axis**: Positive direction is right
- **Y-axis**: Positive direction is up
- **Units**: Meters for distance, kilograms for mass, seconds for time

### Force Superposition

The simulation correctly implements the principle of superposition:
- Net force on each body is the vector sum of forces from all other bodies
- Each pairwise interaction is calculated independently
- Self-interaction is explicitly prevented

## Data File Format

### Structure

```
[number of bodies: int]
[universe radius: double]
[x] [y] [xvel] [yvel] [mass] [image_filename]
...
```

### Example

```
5                    # 5 bodies in simulation
2.50e11             # Universe radius: 2.5 × 10¹¹ meters
1.4960e11  0.0000e00  0.0000e00  2.9800e04  5.9740e24  earth.gif
-1.1055e11 -1.9868e11 2.0336e04 -1.1783e04  6.4190e23  mars.gif
...
```

### Units

- **Position**: Meters from center
- **Velocity**: Meters per second
- **Mass**: Kilograms
- **Time**: Seconds

## Graphics Implementation

### StdDraw Library

The simulation uses `StdDraw.java` for graphics:
- **Canvas Setup**: Scaled to universe radius
- **Animation**: Double buffering for smooth motion
- **Images**: GIF files for celestial body representations

### Rendering Process

1. **Background**: Draw starfield or solid background
2. **Bodies**: Draw each celestial body at current position
3. **Buffer Swap**: Display completed frame
4. **Timing**: Control animation speed with appropriate delays

## Testing Framework

### Unit Tests

Comprehensive test suite validates each component:

- `TestBodyConstructorGetters` - Constructor and accessor methods
- `TestCalcDistance` - Distance calculations
- `TestCalcForceExertedBy` - Force magnitude calculations  
- `TestCalcForceExertedByXY` - Force component calculations
- `TestCalcNetForceExertedByXY` - Net force calculations
- `TestUpdate` - Position/velocity updates
- `TestReadRadius` - File reading for radius
- `TestReadBodies` - File reading for body data

### Test Execution

```bash
javac *.java
java TestBodyConstructorGetters
java TestCalcDistance
# ... continue for all tests
```

## Performance Characteristics

### Time Complexity

- **Per time step**: O(n²) where n is the number of bodies
- **Total simulation**: O(n² × steps) where steps = totalTime / dt
- **Bottleneck**: Pairwise force calculations between all bodies

### Space Complexity

- **Storage**: O(n) for body data and force arrays
- **Temporary**: Minimal additional space required

### Optimization Opportunities

For larger simulations, consider:
- **Barnes-Hut algorithm**: O(n log n) complexity for distant approximations
- **Parallel processing**: Force calculations can be parallelized
- **Adaptive time stepping**: Variable dt based on system dynamics

## Common Implementation Pitfalls

### Numerical Issues

1. **Order of operations**: Update all bodies simultaneously, not sequentially
2. **Self-interaction**: Always check `this.equals(other)` in net force calculations
3. **Division by zero**: Ensure bodies don't occupy identical positions

### Physics Accuracy

1. **Time step size**: Too large causes instability, too small is inefficient
2. **Force calculation order**: Use `F*dx/r` not `F/r*dx` for numerical precision
3. **Unit consistency**: Ensure all values use consistent units

### Graphics Issues

1. **Scaling**: Proper canvas scaling for astronomical distances
2. **Performance**: Limit animation frame rate for smooth display
3. **Image loading**: Verify image files exist and are accessible

This implementation provides a solid foundation for understanding gravitational simulation, numerical methods, and object-oriented design in Java.
