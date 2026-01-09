package com.grupo.tpFinal.dto;

public class TicketsStatsDTO {

    private long total;
    private long abiertos;
    private long enProceso;
    private long cerrados;

    public TicketsStatsDTO(long total, long abiertos, long enProceso, long cerrados) {
        this.total = total;
        this.abiertos = abiertos;
        this.enProceso = enProceso;
        this.cerrados = cerrados;
    }


    public long getAbiertos() {
        return abiertos;
    }

    public long getCerrados() {
        return cerrados;
    }

    public long getEnProceso() {
        return enProceso;
    }

    public long getTotal() {
        return total;
    }
}
