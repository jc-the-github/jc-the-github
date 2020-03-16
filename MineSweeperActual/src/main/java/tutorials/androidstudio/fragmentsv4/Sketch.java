package tutorials.androidstudio.fragmentsv4;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Sketch extends PApplet {

// Daniel Shiffman
// http://codingtra.in
// http://patreon.com/codingtrain
// Processing transcription: Chuck England
// Steering Evolution
// Another version:
// https://github.com/shiffman/NOC-S17-2-Intelligence-Learning/tree/master/week2-evolution/01_evolve_steering

    List<Vehicle> vehicles = new ArrayList<Vehicle>();
    List<Vehicle> savedVehicles = new ArrayList<Vehicle>();
    List<Food> food = new ArrayList<Food>();
    List<Food> poison = new ArrayList<Food>();
    int constant = 1;
    boolean debug;
    boolean complete;
    int squareC;
    float mr = 0.01f;

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"CC_069_steering_evolution"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }

    public void setup() {

        for (int i = 0; i < 50; i++) {
            float x = random(width);
            float y = random(height);
            vehicles.add(new Vehicle(x, y));

        }

        for (int i = 0; i < 40 * constant; i++) {
            float nut = .2f;
            food.add(new Food(nut));
        }

        for (int i = 0; i < 20 * constant; i++) {
            float nut = -1;
            poison.add(new Food(nut));
        }
    }

    public void mousePressed() {
        if (overRect()) {
            if (debug) {
                debug = false;
                squareC = color(0);
            } else {
                debug = true;
                squareC = color(155);
            }

        }
    }

    public void mouseDragged() {
        vehicles.add(new Vehicle(mouseX, mouseY));
    }

    public boolean overRect() {
        return mouseX >= 30 && mouseX <= 30 + 30 &&
                mouseY >= 30 && mouseY <= 30 + 30;
    }

    public void draw() {
        background(51);

        if (debug) {
            fill(squareC);
        } else {
            fill(squareC);
        }
        stroke(255);
        rect(30, 30, 30, 30);

        if (random(1) < 0.1f) {
            float nut = .2f;
            food.add(new Food(nut));
        }

        if (random(1) < 0.01f) {
            float nut = -1f;
            poison.add(new Food(nut));
        }

        for (int i = 0; i < food.size(); i++) {
            food.get(i).update();
            if (food.get(i).dead()) {
                food.remove(i);
            }
        }
        for (int i = 0; i < food.size(); i++) {
            food.get(i).display();
        }

        for (int i = 0; i < poison.size(); i++) {
            poison.get(i).update();
        }
        for (int i = 0; i < poison.size(); i++) {
            poison.get(i).display();
        }

        for (int i = vehicles.size() - 1; i >= 0; i--) {
            Vehicle v = vehicles.get(i);
            v.boundaries();
            v.behaviors(food, poison);
            v.update();
            v.display();

            Vehicle newVehicle = v.clone();
            if (newVehicle != null) {
                vehicles.add(newVehicle);
                v.clonesProduced++;
            }

            if (v.dead()) {
                savedVehicles.add(v);
                vehicles.remove(i);
            }
            if (vehicles.size() == 0 && !complete) {
                data();

            }
        }
    }

    public void data() {
        complete = true;
        for (int i = savedVehicles.size() - 1; i >= 0; i--) {
            System.out.println("MaxSize, Vehicle " + i + ": " + savedVehicles.get(i).maxSize);
            System.out.println("Clones Produced, Vehicle " + i + ": " + savedVehicles.get(i).clonesProduced);
            System.out.println("Food Eaten, Vehicle " + i + ": " + savedVehicles.get(i).foodEaten);

        }
    }

    public void settings() {
        size(width, height);
    }

    class Food {

        PVector position;
        float x = random(20, width - 20);
        float y = random(20, height - 20);
        float size;
        float health;
        boolean poison;

        Food(float nut) {
            poison = nut == -1;
            position = new PVector(x, y);
            size = random(12, 16);

            health = 1;
        }

        // Method to update location
        public void update() {
            if (health <= 0.05) {

            } else {
                health -= .01;

            }
            System.out.println("h" + health);
        }

        public boolean dead() {
            return size < 0;
        }

        public void display() {

            if (!poison) {     //normal food

                int lg = color(144, 238, 144);
                int dg = color(0, 100, 0);
                int col = lerpColor(dg, lg, health);

                fill(col);
                stroke(col);
                ellipse(x, y, size, size);
            } else {          //poison

                int lr = color(250, 128, 114);
                int dr = color(128, 0, 0);
                int col = lerpColor(dr, lr, health);

                fill(col);
                stroke(col);
                ellipse(x, y, size, size);
            }
        }
    }

    class Vehicle {
        PVector acceleration;
        PVector velocity;
        PVector position;
        float r;
        float maxspeed;
        float maxforce;
        float health = 1;
        float[] dna;

        float maxSize;
        float foodEaten;
        float clonesProduced;

        Vehicle(float x, float y) {
            this(x, y, null);
        }

        Vehicle(float x, float y, float[] dna_) {

            dna = new float[5];
            if (dna_ == null) {
                // Food weight
                dna[0] = random(-2, 2);
                // Poison weight
                dna[1] = random(-2, 2);
                // Food perception
                dna[2] = random(0, 100);
                // Poision Percepton
                dna[3] = random(0, 100);
                // size
                dna[4] = random(4, 7);
                r = this.dna[4] * constant;
                maxSize = r;
            } else {
                // Mutation
                dna[0] = dna_[0];
                if (random(1) < mr) {
                    dna[0] += random(-0.1f, 0.1f);
                }
                dna[1] = dna_[1];
                if (random(1) < mr) {
                    dna[1] += random(-0.1f, 0.1f);
                }
                dna[2] = dna_[2];
                if (random(1) < mr) {
                    dna[2] += random(-10, 10);
                }
                dna[3] = dna_[3];
                if (random(1) < mr) {
                    dna[3] += random(-10, 10);
                }
                dna[4] = dna_[4];
                if (random(1) < mr) {
                    dna[4] += random(-.05f, .05f);
                }
                r = dna[4] * constant;
                maxSize = r;
            }
            acceleration = new PVector(0, 0);
            velocity = new PVector(0, -2);
            position = new PVector(x, y);
            //r = 4;
            maxspeed = 10 / r;
            maxforce = 0.5f;

            health = r * .25f;
        }

        // Method to update location
        public void update() {
            health -= 0.005f;

            // Update velocity
            velocity.add(acceleration);
            // Limit speed
            velocity.limit(maxspeed);
            position.add(velocity);
            // Reset accelerationelertion to 0 each cycle
            acceleration.mult(0);
        }

        public void applyForce(PVector force) {
            // We could add mass here if we want A = F / M
            acceleration.add(force);
        }

        public void behaviors(List<Food> good, List<Food> bad) {
            PVector steerG = eat(good, 0.2f, dna[2]);
            PVector steerB = eat(bad, -1, dna[3]);

            steerG.mult(dna[0]);
            steerB.mult(dna[1]);

            applyForce(steerG);
            applyForce(steerB);
        }

        public Vehicle clone() {
            if (random(1) < 0.002f) {
                return new Vehicle(position.x, position.y, dna);
            } else {
                return null;
            }
        }

        public PVector eat(List<Food> list, float nutrition, float perception) {
            float record = Float.POSITIVE_INFINITY;
            PVector closest = null;
            for (int i = list.size() - 1; i >= 0; i--) {
                float d = position.dist(list.get(i).position) - list.get(i).size - dna[4];

                if (d < maxspeed) {
                    list.remove(i);

                    health += nutrition;
                    r += nutrition;
                    maxSize += nutrition;

                    dna[4] += nutrition;
                    dna[2] += nutrition * 10;

                    foodEaten++;
                } else {
                    if (d < record && d < perception) {
                        record = d;
                        closest = list.get(i).position;
                    }
                }
            }

            // This is the moment of eating!

            if (closest != null) {
                return seek(closest);
            }

            return new PVector(0, 0);
        }

        // A method that calculates a steering force towards a target
        // STEER = DESIRED MINUS VELOCITY
        public PVector seek(PVector target) {
            PVector desired = PVector.sub(target, position); // A vector pointing from the location to the target

            // Scale to maximum speed
            desired.setMag(maxspeed);

            // Steering = Desired minus velocity
            PVector steer = PVector.sub(desired, velocity);
            steer.limit(maxforce); // Limit to maximum steering force

            return steer;
            //applyForce(steer);
        }

        public boolean dead() {
            return health < 0;
        }

        public void display() {
            // Draw a triangle rotated in the direction of velocity
            float angle = velocity.heading() + PI / 2;

            pushMatrix();
            translate(position.x, position.y);
            rotate(angle);


            if (debug) {
                strokeWeight(3);
                stroke(0, 255, 0);
                noFill();
                line(0, 0, 0, -dna[0] * 25);
                strokeWeight(2);
                ellipse(0, 0, dna[2] * 2, dna[2] * 2);
                stroke(255, 0, 0);
                line(0, 0, 0, -dna[1] * 25);
                ellipse(0, 0, dna[3] * 2, dna[3] * 2);
            }

            int gr = color(0, 255, 0);
            int rd = color(255, 0, 0);
            int col = lerpColor(rd, gr, health, 1);

            fill(col);
            stroke(col);
            strokeWeight(1);
            beginShape();
            vertex(0, -r);
            vertex(-r, r);
            vertex(r, r);
            endShape(CLOSE);

            popMatrix();
        }


        public void boundaries() {
            float d = 25;

            PVector desired = null;

            if (position.x < d) {
                desired = new PVector(maxspeed, velocity.y);
            } else if (position.x > width - d) {
                desired = new PVector(-maxspeed, velocity.y);
            }

            if (position.y < d) {
                desired = new PVector(velocity.x, maxspeed);
            } else if (position.y > height - d) {
                desired = new PVector(velocity.x, -maxspeed);
            }

            if (desired != null) {
                desired.normalize();
                desired.mult(maxspeed);
                PVector steer = PVector.sub(desired, velocity);
                steer.limit(maxforce);
                applyForce(steer);
            }
        }
    }
}

