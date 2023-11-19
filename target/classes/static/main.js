function text(x) {
    if (x == 0) {
        document.getElementById("Voice").hidden=false;
        document.getElementById("apenas_dados").hidden=true;
        }

    else if (x == 1) {
        document.getElementById("Voice").hidden=true;
        document.getElementById("apenas_dados").hidden=false;}
    return;
}

function ocultar()
{
    document.getElementById("Voice").hidden=true;
    document.getElementById("apenas_dados").hidden=true;
}

