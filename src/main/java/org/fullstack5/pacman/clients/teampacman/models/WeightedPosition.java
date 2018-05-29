package org.fullstack5.pacman.clients.teampacman.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.fullstack5.pacman.api.models.Direction;
import org.fullstack5.pacman.api.models.Position;

import java.util.Objects;

/**
 * A position with weights considering its distance to source and target in pathfinding.
 */
@Getter
@AllArgsConstructor
public final class WeightedPosition implements Comparable<WeightedPosition> {
    private final Position position;
    private final WeightedPosition previous;
    private final Direction directionToPosition;
    private final int distanceFromSource;
    private final int estimatedDistanceToTarget;
//    private boolean done;

    public final int getEstimatedTotalDistance() {
        return getDistanceFromSource() + getEstimatedDistanceToTarget();
    }

    @Override
    public final int compareTo(final WeightedPosition other) {
//        if (isDone() && !other.isDone()) {
//            return 1;
//        }
//        if (!isDone() && other.isDone()) {
//            return -1;
//        }
//        if (isDone() ^ other.isDone()) {
//            System.out.println(String.format("%s ^ %s : %d - %d = %d", isDone(), other.isDone(), isDone() ? 1 : 0, other.isDone() ? 1 : 0, isDone() ? 1 : 0 - (other.isDone() ? 1 : 0)));
//            return isDone() ? 1 : 0 - (other.isDone() ? 1 : 0);
//        }
        if (getEstimatedTotalDistance() != other.getEstimatedTotalDistance()) {
            return getEstimatedTotalDistance() - other.getEstimatedTotalDistance();
        }
        if (getEstimatedDistanceToTarget() != other.getEstimatedDistanceToTarget()) {
            return getEstimatedDistanceToTarget() - other.getEstimatedDistanceToTarget();
        }
        return Integer.compare(getPosition().getX(), other.getPosition().getX());
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof WeightedPosition)) {
            return false;
        }
        final WeightedPosition other = (WeightedPosition) obj;
        return getPosition().equals(other.getPosition())
//                && isDone() == other.isDone()
                && getDistanceFromSource() == other.getDistanceFromSource()
                && getEstimatedDistanceToTarget() == other.getEstimatedDistanceToTarget();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(position, /*isDone(), */getDistanceFromSource(), getEstimatedDistanceToTarget());
    }

//    public void setDone() {
//        this.done = true;
//    }

    @Override
    public String toString() {
//        return String.format("[%d, %d] from: %d to: %d total: %d done: %s", getPosition().getX(), getPosition().getY(), getDistanceFromSource(), getEstimatedDistanceToTarget(), getEstimatedTotalDistance(), isDone());
        return String.format("[%d, %d] from: %d to: %d total: %d", getPosition().getX(), getPosition().getY(), getDistanceFromSource(), getEstimatedDistanceToTarget(), getEstimatedTotalDistance());
    }
}
