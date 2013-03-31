package com.littleGreenMan.Pong_Madness.model;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table SIMPLE_PARTICIPANT.
 */
public class SimpleParticipant {

    private Long id;
    private Integer score;
    private Long simpleGameId;
    private Long playerId;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public SimpleParticipant() {
    }

    public SimpleParticipant(Long id) {
        this.id = id;
    }

    public SimpleParticipant(Long id, Integer score, Long simpleGameId, Long playerId) {
        this.id = id;
        this.score = score;
        this.simpleGameId = simpleGameId;
        this.playerId = playerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getSimpleGameId() {
        return simpleGameId;
    }

    public void setSimpleGameId(Long simpleGameId) {
        this.simpleGameId = simpleGameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
