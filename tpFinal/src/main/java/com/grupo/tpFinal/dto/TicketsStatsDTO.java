package com.grupo.tpFinal.dto;

public class TicketsStatsDTO {

    private long total;
    private long noAtendidos;
    private long enProceso;
    private long cerrados;

    public TicketsStatsDTO(long total, long noAtendidos, long enProceso, long cerrados) {
        this.total = total;
        this.noAtendidos = noAtendidos;
        this.enProceso = enProceso;
        this.cerrados = cerrados;
    }


    public long getnoAtendidos() {
        return noAtendidos;
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
