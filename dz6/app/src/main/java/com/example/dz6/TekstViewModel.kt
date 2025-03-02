package com.example.dz6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TekstViewModel : ViewModel() {

    private val _slideText = MutableLiveData<String>()
    val slideText: LiveData<String> get() = _slideText

    fun updateText(chooseString: String) {
        when (chooseString) {
            "1" -> _slideText.value = "Sam proces origamija bazira se na savijanju jednog komada papira, bez sečenja ili lepljenja, u različite oblike koji predstavljaju životinje, biljke, geometrijske oblike, i druge figure.Svaki pokret je precizno izveden da stvori određeni deo oblika, čime umetnik mora da razvije veštinu preciznosti i strpljenja U osnovi, origami zahteva posvećenost i fokus, jer se svaka greška može odraziti na konačni rezultat.Različiti modeli zahtevaju različite tehnike savijanja, kao što su osnovni pregibi, preklapanja, i kompresije.Origami se tako može smatrati meditativnom praksom, koja kombinuje umetnički izraz sa tehnikom i matematičkom logikom.To nije samo umetnost, već i nauka o geometriji, gde umetnik koristi svoje razumevanje prostora i forme da stvori nešto lepo iz običnog papira."
            "3" -> _slideText.value = "Origami, drevna japanska umetnost savijanja papira, datira iz perioda Heian (794-1185), mada se veruje da su tehnike razvijane još ranije u Kini.U početku je bio vezan za ceremonijalne svrhe i religiozne obrede, a korišćen je i u samurajskim ritualima darivanja.Prvobitno ograničen na elitu zbog retkosti papira, origami je kasnije postao dostupan širom društva i postao kulturno blago Japana.Tokom vekova, origami se razvijao kroz različite stilove i tehnike, od jednostavnih figura do složenih umetničkih dela Danas, umetnici i hobisti širom sveta praktikuju ovu umetnost, stvarajući kompleksne strukture koje nadmašuju granice mašte.U savremenoj eri, origami nije samo umetnost, već i simbol inovacije i kreativnosti, koji prevazilazi kulturne granice i inspiriše generacije."
            "2" -> _slideText.value = "Danas, origami nije samo umetnost, već se koristi u raznim naučnim i tehničkim oblastima kao što su medicina, inženjering i svemirska istraživanja.Stručnjaci primenjuju principe origamija za dizajniranje kompaktnih struktura poput solarnih panela koji se mogu preklapati i razmotavati u svemiru,kao i u biomedicinskim uređajima kao što su stentovi i mikroskopske robotike.Razvoj tehnologije i materijala dodatno je proširio primenu origamija, omogućavajući istraživačima da stvaraju inovativne i efikasne rešenja za kompleksne probleme.Tako, origami zadržava svoju simboličku vrednost dok se istovremeno razvija kao alat za inovaciju i primenu u modernom svetu,povezujući umetnost sa naukom, tehnologijom i inženjeringom, i podstičući kreativno razmišljanje."

            else -> _slideText.value = ""
        }
    }
}
