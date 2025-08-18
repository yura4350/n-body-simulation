

# N-Body Simulation

A Java implementation of an N-body gravitational simulation that models the motion of celestial bodies under mutual gravitational forces. This project creates beautiful animated visualizations of planetary systems, binary stars, galaxies, and other astronomical phenomena.

## Overview

This simulation is based on Newton's laws of gravitation and motion, using numerical integration to approximate the complex gravitational interactions between multiple celestial bodies. The program reads initial conditions from data files and creates real-time animations of the resulting orbital mechanics.

**Background:** In 1687, Isaac Newton formulated the principles governing the motion of two particles under the influence of their mutual gravitational attraction in his famous _Principia Mathematica_. However, Newton was unable to solve the problem for three particles. Indeed, in general, solutions to systems of three or more particles must be approximated via numerical simulations.

This program simulates the motion of _N_ objects in a plane, mutually affected by gravitational forces, and animates the results. Such methods are widely used in cosmology, semiconductors, and fluid dynamics to study complex physical systems. The simulation consists of a driver program `NBody.java` that runs the simulation and creates an animation of bodies moving in space interacting with each other subject to mutual gravitational forces. These bodies are modeled by the class `CelestialBody.java`.

Below you can see an animation of the simulation running with some planets in our solar system.

### Example Simulation

<div align="center">
  <img width="500" height="500" src="figures/planets.gif">
</div>

### Features

- **Real-time animation** of gravitational interactions
- **Multiple simulation scenarios** including solar systems, binary stars, galaxies, and chaotic systems
- **Accurate physics modeling** using Newton's laws of gravitation
- **Custom visualization** with planetary images and smooth animation
- **Configurable simulations** through data file input

## Project Structure

```
planet-simulation/
├── src/                    # Source code
│   ├── CelestialBody.java  # Individual celestial body class
│   ├── NBody.java          # Main simulation driver
│   ├── StdDraw.java        # Graphics library
│   └── Test*.java          # Unit test classes
├── data/                   # Simulation scenarios
│   ├── planets.txt         # Our solar system
│   ├── binary.txt          # Binary star system
│   ├── galaxy.txt          # Galaxy formation
│   └── ...                 # Many more scenarios
├── images/                 # Celestial body graphics
└── figures/                # Documentation images
```

## Implementation Details

The simulation consists of two main classes:

- **`CelestialBody.java`**: Represents individual celestial bodies such as planets or stars
- **`NBody.java`**: The main simulation driver that reads data files and runs the animation

The simulation reads initial conditions from data files and then simulates gravitational interactions over time, creating beautiful animations of orbital mechanics.

### CelestialBody Class

The `CelestialBody` class represents individual astronomical objects with:

- **Position**: x, y coordinates
- **Velocity**: x, y velocity components  
- **Mass**: gravitational mass
- **Image**: visual representation

Key methods include:

- `calcDistance()` - Euclidean distance between bodies
- `calcForceExertedBy()` - Gravitational force calculation
- `calcForceExertedByX/Y()` - Force components
- `calcNetForceExertedByX/Y()` - Net force from all other bodies
- `update()` - Position and velocity integration

These methods use physics calculations to determine gravitational forces and update object positions during the simulation.

### NBody Class

The main simulation driver that:
- Reads simulation data from files
- Manages the simulation loop
- Handles graphics and animation
- Implements numerical integration

## Physics Implementation

### Gravitational Force

The simulation uses Newton's law of universal gravitation:

```
F = G * m₁ * m₂ / r²
```

Where:
- `G` = 6.67 × 10⁻¹¹ (gravitational constant)
- `m₁, m₂` = masses of the two bodies
- `r` = distance between bodies

### Force Components

Force is decomposed into x and y components:

```
Fₓ = F * (dx / r)
Fᵧ = F * (dy / r)
```

### Numerical Integration

The simulation uses Euler's method for integration:

1. Calculate acceleration: `a = F / m`
2. Update velocity: `v_new = v_old + a * dt`
3. Update position: `x_new = x_old + v_new * dt`

## Data Format

Simulation files follow this format:

```
[number of bodies]
[universe radius]
[x_pos] [y_pos] [x_vel] [y_vel] [mass] [image_file]
...
```

Example (`data/planets.txt`):
```
5
2.50e11
1.4960e11  0.0000e00  0.0000e00  2.9800e04  5.9740e24  earth.gif
-1.1055e11 -1.9868e11 2.0336e04 -1.1783e04  6.4190e23  mars.gif
...
``` 


## How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt

### Compilation and Execution

1. **Navigate to the project directory:**
   ```bash
   cd /path/to/planet-simulation
   ```

2. **Compile the Java files:**
   ```bash
   javac src/*.java
   ```

3. **Run the simulation:**
   ```bash
   java -cp src NBody
   ```

### Alternative Method

Run from the src directory:
```bash
cd src
javac *.java
java NBody
```


## Available Simulations

The `data/` folder contains numerous pre-configured scenarios:

| File | Description |
|------|-----------|
| `planets.txt` | Our solar system |
| `binary.txt` | Binary star system |
| `galaxy.txt` | Galaxy formation |
| `chaosblossom.txt` | Chaotic orbital patterns |
| `atom.txt` | Atomic-like simulation |
| `entropy-universe.txt` | High-entropy system |
| `kaleidoscope.txt` | Symmetric patterns |
| `massive-squirrel-battle.txt` | Fun scenario |

## Output

When the simulation completes, it prints the final state of all bodies:

```
5
2.50e11
1.4631e09  1.4943e11 -2.9831e04  4.0749e02  5.9740e24  earth.gif
-1.1174e11 -1.9803e11 2.0989e04 -1.1953e04  6.4190e23  mars.gif
...
```

## Testing

The project includes comprehensive unit tests:

```bash
java -cp src TestBodyConstructorGetters
java -cp src TestCalcDistance
java -cp src TestCalcForceExertedBy
java -cp src TestCalcForceExertedByXY
java -cp src TestCalcNetForceExertedByXY
java -cp src TestUpdate
java -cp src TestReadRadius
java -cp src TestReadBodies
```

## Dependencies

This project uses only one external library:
- **StdDraw.java**: A simple graphics library for Java animations and visualizations

All physics calculations and simulation logic are implemented from scratch.


## Technical Details

### Performance Considerations

- **Time Complexity**: O(n²) per time step for n bodies
- **Integration Method**: Euler's method with configurable time step
- **Collision Detection**: Bodies can pass through each other (gravitational simulation only)

### Accuracy and Stability

- Smaller time steps (`dt`) provide better accuracy but slower performance
- Large time steps can cause numerical instability
- The simulation assumes point masses (no collision handling)

## Educational Value

This project demonstrates:
- **Object-oriented programming** principles
- **Numerical simulation** techniques
- **Physics modeling** in software
- **Algorithm complexity** analysis
- **Scientific computing** methods


## Troubleshooting

### Common Issues

1. **Graphics window doesn't appear**: Ensure Java has GUI permissions
2. **Simulation runs too fast/slow**: Adjust the `dt` parameter in the code
3. **Mac security warning**: Safe to ignore warnings about "Secure coding"

### Performance Tips

- Use smaller universe files for faster testing
- Increase `dt` for faster (less accurate) simulations
- Decrease `dt` for more accurate (slower) simulations

## Future Enhancements

Potential improvements could include:
- Adaptive time stepping
- Collision detection and response
- 3D visualization
- Relativistic effects
- Barnes-Hut algorithm for better performance
- Interactive parameter adjustment

## License

This project is for educational purposes. The StdDraw library maintains its own licensing terms.




