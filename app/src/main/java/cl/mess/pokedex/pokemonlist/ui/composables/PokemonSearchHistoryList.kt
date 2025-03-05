package cl.mess.pokedex.pokemonlist.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.mess.pokedex.pokemonlist.data.local.entities.SearchHistory
import cl.mess.pokedex.ui.composables.AttrsPokemonText
import cl.mess.pokedex.ui.composables.PokemonText

@Composable
fun PokemonSearchHistoryList(
    filteredTerms: List<SearchHistory>,
    onSearchTermClick: (String) -> Unit
) {
    if (filteredTerms.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White.copy(alpha = 0.95f))
        ) {
            items(filteredTerms) { history ->
                PokemonText(
                    attrs = AttrsPokemonText(
                        text = history.searchTerm,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSearchTermClick(history.searchTerm)
                            }
                            .padding(8.dp),
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                )
            }
        }
    }
}
